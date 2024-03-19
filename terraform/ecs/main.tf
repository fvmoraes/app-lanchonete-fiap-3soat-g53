terraform {
  required_version = "1.7.5"

  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "5.0"
    }
    random = {
      source  = "hashicorp/random"
      version = "3.4.3"
    }
  }
}

provider "aws" {
  access_key = var.aws_access_key
  secret_key = var.aws_secret_key
  region     = var.aws_region
}
# ECS
resource "aws_ecs_cluster" "this" {
  name = var.cluster_name
}

resource "aws_ecs_task_definition" "this" {
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
        "value": "${var.database_url}"
        },
        {
          "name": "DB_USER",
          "value": "${var.database_user}"
        },
        {
          "name": "DB_PASS",
          "value": "${var.database_password}"
        }
      ]
    }
  ]
  DEFINITION
  requires_compatibilities = ["FARGATE"]
  network_mode             = "awsvpc"
  memory                   = var.memory
  cpu                      = var.cpu
  execution_role_arn       = aws_iam_role.ecsTaskExecutionRole3SoatG53.arn
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

resource "aws_ecs_service" "this" {
  name                = var.cluster_service
  cluster             = aws_ecs_cluster.this.id
  task_definition     = aws_ecs_task_definition.this.arn
  launch_type         = "FARGATE"
  scheduling_strategy = "REPLICA"
  desired_count       = var.desired_capacity

  load_balancer {
    target_group_arn = aws_lb_target_group.this.arn
    container_name   = aws_ecs_task_definition.this.family
    container_port   = var.container_port
  }

  network_configuration {
    subnets          = [aws_subnet.this["pub_a"].id, aws_subnet.this["pub_b"].id]
    security_groups  = [aws_security_group.this.id]
    assign_public_ip = true
  }

}

resource "aws_security_group" "this" {
  name        = "ECS-3SOAT-G53-TASK-SG"
  description = "SG-TASK-3SOAT-G53"
  vpc_id      = aws_vpc.this.id

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

# RDS
resource "aws_security_group" "rds" {
  name        = "RDS-SG-3SOAT-G53"
  description = "SG-RDS-3SOAT-G53"
  vpc_id      = aws_vpc.this.id

  ingress {
    from_port   = var.database_port
    to_port     = var.database_port
    protocol    = "tcp"
    security_groups = [aws_security_group.alb_rds.id] # Allow access from ECS security group
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "RDS-SG-3SOAT-G53"
  }
}

resource "aws_db_subnet_group" "db-sgb" {
  name       = "db-sgb"
  subnet_ids = [aws_subnet.this["pub_a"].id, aws_subnet.this["pub_b"].id]

  tags = {
    Name = "RDS-SBG-3SOAT-G53"
  }
}

# resource "aws_db_instance" "rds-db-3soat-g53" {
#   identifier           = "rds-db-3soat-g53"
#   allocated_storage    = 10
#   storage_type         = "gp2"
#   engine               = "postgres"
#   engine_version       = "15.5"
#   instance_class       = "db.t3.micro"
#   db_subnet_group_name = aws_db_subnet_group.db-sgb.name
#   db_name              = var.database_name
#   username             = var.database_user
#   password             = var.database_password
#   tags = {
#     Name = "RDS-DB-3SOAT-G53"
#   }
# }

# TEST
resource "aws_ecs_task_definition" "task-test-3soat-g53" {
  family                   = var.cluster_task_test
  container_definitions    = <<DEFINITION
  [
    {
      "name": "${var.cluster_task_test}",
      "image": "${var.image_url_test}",
      "essential": true,
      "portMappings": [
        {
          "containerPort": ${var.container_port_test},
          "hostPort": ${var.container_port_test}
        }
      ],
      "memory": ${var.memory_test},
      "cpu": ${var.cpu_test}
    }
  ]
  DEFINITION
  requires_compatibilities = ["FARGATE"]
  network_mode             = "awsvpc"
  memory                   = var.memory_test
  cpu                      = var.cpu_test
  execution_role_arn       = aws_iam_role.ecsTaskExecutionRole3SoatG53.arn
}

resource "aws_ecs_service" "service-test-3soat-g53" {
  name                = var.cluster_service_test
  cluster             = aws_ecs_cluster.this.id
  task_definition     = aws_ecs_task_definition.task-test-3soat-g53.arn
  launch_type         = "FARGATE"
  scheduling_strategy = "REPLICA"
  desired_count       = var.desired_capacity_test

  load_balancer {
    target_group_arn = aws_lb_target_group.alb_tg_test.arn
    container_name   = aws_ecs_task_definition.task-test-3soat-g53.family
    container_port   = var.container_port_test
  }

  network_configuration {
    subnets          = [aws_subnet.this["pub_a"].id, aws_subnet.this["pub_b"].id]
    security_groups  = [aws_security_group.sg_task_test.id]
    assign_public_ip = true
  }

}

resource "aws_security_group" "sg_task_test" {
  name        = "ECS-3SOAT-G53-TASK-TEST-SG"
  description = "SG-TASK-TEST-3SOAT-G53"
  vpc_id      = aws_vpc.this.id

  ingress {
    protocol        = "tcp"
    from_port       = var.container_port_test
    to_port         = var.container_port_test
    security_groups = [aws_security_group.alb_test.id]
  }

  egress {
    protocol    = "-1"
    from_port   = 0
    to_port     = 0
    cidr_blocks = ["0.0.0.0/0"]
  }
}