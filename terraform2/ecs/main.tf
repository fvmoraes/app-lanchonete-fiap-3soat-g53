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
      "cpu": ${var.cpu}
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

resource "aws_iam_role_policy_attachment" "ecsTaskExecutionRole_policy" {
  role       = aws_iam_role.ecsTaskExecutionRole3SoatG53.name
  policy_arn = "arn:aws:iam::aws:policy/service-role/AmazonECSTaskExecutionRolePolicy"
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
