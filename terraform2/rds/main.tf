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

resource "aws_db_instance" "app_lanchonete_fiap_3soat_g53_rds" {
  identifier           = "app-lanchonete-fiap-3soat-g53-rds"
  allocated_storage    = 10
  storage_type         = "gp2"
  engine               = "postgres"
  engine_version       = "15.5"
  instance_class       = "db.t3.micro"
  db_name              = "fiap"
  username             = "postgres"
  password             = "postgres"
  db_subnet_group_name = aws_db_subnet_group.app_lanchonete_fiap_3soat_g53_subnet_group.name
  tags = {
    Name = "app_lanchonete_fiap_3soat_g53_db_instance"
  }
}

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
