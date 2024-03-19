# ECS
resource "aws_ecs_cluster" "cluster_3soat_g53" {
  name = var.cluster_name
}

resource "aws_ecs_task_definition" "task_def_3soat_g53" {
  family                   = var.cluster_task
  container_definitions    = <<DEFINITION
  [
    {
      "name": "${var.cluster_task}",
      "image": "${var.image_url}",
      "essential": true,
      "portMappings": [
        {
          "containerPort": ${var.container_port},
          "hostPort": ${var.container_port}
        }
      ],
      "memory": ${var.memory},
      "cpu": ${var.cpu},
      "environment": [
        {
          "name": "CONNECTION_STRING",
          "value": "${var.database_url}${aws_db_instance.rds-db-3soat-g53.endpoint}/fiap"
        },
        {
          "name": "DB_USER",
          "value": "${var.database_user}"
        },
        {
          "name": "DB_PASS",
          "value": "${var.database_password}"
        }
      ],
      "logConfiguration": {
        "logDriver": "awslogs",
        "options": {
          "awslogs-create-group": "true",
          "awslogs-group": "/ecs/3soat-g53-task",
          "awslogs-region": "us-east-1",
          "awslogs-stream-prefix": "ecs"
        },
        "secretOptions": []
      }
    }
  ]
  DEFINITION
  requires_compatibilities = ["FARGATE"]
  network_mode             = "awsvpc"
  memory                   = var.memory
  cpu                      = var.cpu
  execution_role_arn       = aws_iam_role.ecsTaskExecutionRole3SoatG53.arn
  runtime_platform {
      cpu_architecture = "ARM64"
      operating_system_family = "LINUX"
  }
}


resource "aws_iam_role" "ecsTaskExecutionRole3SoatG53" {
  name               = "ecsTaskExecutionRole3SoatG53"
  assume_role_policy = data.aws_iam_policy_document.assume_role_policy.json
}

data "aws_iam_policy_document" "assume_role_policy" {
  statement {
    actions = ["sts:AssumeRole"]

    principals {
      type        = "Service"
      identifiers = ["ecs-tasks.amazonaws.com"]
    }
  }
}

resource "aws_iam_role_policy_attachment" "ecsTaskExecutionRole_policy_ecs" {
  role       = aws_iam_role.ecsTaskExecutionRole3SoatG53.name
  policy_arn = "arn:aws:iam::aws:policy/service-role/AmazonECSTaskExecutionRolePolicy"
}

resource "aws_iam_role_policy_attachment" "ecsTaskExecutionRole_policy_rds" {
  role       = aws_iam_role.ecsTaskExecutionRole3SoatG53.name
  policy_arn = "arn:aws:iam::aws:policy/AmazonRDSFullAccess"
}

resource "aws_ecs_service" "ecs_service_3soat_g53" {
  name                = var.cluster_service
  cluster             = aws_ecs_cluster.cluster_3soat_g53.id
  task_definition     = aws_ecs_task_definition.task_def_3soat_g53.arn
  launch_type         = "FARGATE"
  scheduling_strategy = "REPLICA"
  desired_count       = var.desired_capacity

  load_balancer {
    target_group_arn = aws_lb_target_group.lb_tg_3soat_g53.arn
    container_name   = aws_ecs_task_definition.task_def_3soat_g53.family
    container_port   = var.container_port
  }

  network_configuration {
    subnets          = [aws_subnet.sbn_3soat_g53["pub_a"].id, aws_subnet.sbn_3soat_g53["pub_b"].id]
    security_groups  = [aws_security_group.task_3soat_g53.id]
    assign_public_ip = true
  }
}

resource "aws_security_group" "task_3soat_g53" {
  name        = "SG-TASK-3SOAT-G53"
  description = "SG-TASK-3SOAT-G53"
  vpc_id      = aws_vpc.vpc_3soat_g53.id

  ingress {
    protocol        = "tcp"
    from_port       = var.container_port
    to_port         = var.container_port
    security_groups = [aws_security_group.alb.id]
  }

  egress {
    protocol    = "-1"
    from_port   = 0
    to_port     = 0
    cidr_blocks = ["0.0.0.0/0"]
  }
}
