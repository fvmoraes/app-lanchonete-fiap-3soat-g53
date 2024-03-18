resource "aws_lb" "app_lanchonete_fiap_3soat_g53_alb" {
  name               = "3soat-g53-alb"
  internal           = false
  load_balancer_type = "application"
  subnets            = [aws_subnet.app_lanchonete_fiap_3soat_g53_subnet1.id, aws_subnet.app_lanchonete_fiap_3soat_g53_subnet2.id]
  security_groups    = [aws_security_group.app_lanchonete_fiap_3soat_g53_sg.id]
  tags = {
    Name = "app-lanchonete-fiap-3soat-g53-alb"
  }
}

resource "aws_lb_target_group" "app_lanchonete_fiap_3soat_g53_target_group" {
  name        = "3soat-g53-target-group"
  port        = 8080
  protocol    = "HTTP"
  target_type = "ip"
  vpc_id      = aws_vpc.app_lanchonete_fiap_3soat_g53_main.id
  tags = {
    Name = "app-lanchonete-fiap-3soat-g53-target-group"
  }
}

resource "aws_lb_listener" "app_lanchonete_fiap_3soat_g53_listener" {
  load_balancer_arn = aws_lb.app_lanchonete_fiap_3soat_g53_alb.arn
  port              = 8080
  protocol          = "HTTP"

  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.app_lanchonete_fiap_3soat_g53_target_group.arn
  }
}
