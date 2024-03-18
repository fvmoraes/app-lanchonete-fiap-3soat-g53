resource "aws_ecs_cluster" "app_lanchonete_fiap_3soat_g53_cluster" {
  name = "app_lanchonete_fiap_3soat_g53_cluster"
  tags = {
    Name = "app_lanchonete_fiap_3soat_g53_cluster"
  }
}

resource "aws_ecs_task_definition" "app_lanchonete_fiap_3soat_g53_task" {
  family                   = "app_lanchonete_fiap_3soat_g53_task"
  container_definitions    = file("${path.module}/container-definition.json")
  requires_compatibilities = ["FARGATE"]
  network_mode             = "awsvpc"
  execution_role_arn       = aws_iam_role.ecs_task_execution_role.arn
  task_role_arn            = aws_iam_role.ecs_task_role.arn

  cpu    = "256"
  memory = "512"
  tags = {
    Name = "app_lanchonete_fiap_3soat_g53_task_definition"
  }
}

resource "aws_ecs_service" "app_lanchonete_fiap_3soat_g53_service" {
  name            = "app_lanchonete_fiap_3soat_g53_service"
  cluster         = aws_ecs_cluster.app_lanchonete_fiap_3soat_g53_cluster.id
  task_definition = aws_ecs_task_definition.app_lanchonete_fiap_3soat_g53_task.arn
  desired_count   = 1
  launch_type     = "FARGATE"
  network_configuration {
    subnets          = [aws_subnet.app_lanchonete_fiap_3soat_g53_subnet1.id]
    security_groups  = [aws_security_group.app_lanchonete_fiap_3soat_g53_sg.id]
    assign_public_ip = true
  }
  load_balancer {
    target_group_arn = aws_lb_target_group.app_lanchonete_fiap_3soat_g53_target_group.arn
    container_name   = "app_lanchonete_fiap_3soat_g53_container"
    container_port   = 8080
  }
  tags = {
    Name = "app_lanchonete_fiap_3soat_g53_ecs_service"
  }
}
