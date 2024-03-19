resource "aws_lb" "lb_3soat_g53" {
  name               = "ECS-3SOAT-G53-ALB"
  security_groups    = [aws_security_group.alb.id]
  load_balancer_type = "application"
  internal = true
  subnets = [aws_subnet.sbn_3soat_g53["pub_a"].id, aws_subnet.sbn_3soat_g53["pub_b"].id]
  tags = merge(local.common_tags, { Name = "ECS-3SOAT-G53-ALB" })
}

resource "aws_lb_target_group" "lb_tg_3soat_g53" {
  name        = "ALB-TG-3SOAT-G53"
  port        = 8080
  protocol    = "HTTP"
  target_type = "ip"
  vpc_id      = aws_vpc.vpc_3soat_g53.id

  health_check {
    healthy_threshold   = "3"
    interval            = "30"
    protocol            = "HTTP"
    matcher             = "200,301,302"
    path                = "/"
    timeout             = "5"
    unhealthy_threshold = "5"
  }
}

resource "aws_lb_listener" "lb_listener_3soat_g53" {
  load_balancer_arn = aws_lb.lb_3soat_g53.arn
  port              = 8080
  protocol          = "HTTP"

  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.lb_tg_3soat_g53.arn
  }
}

resource "aws_security_group" "alb" {
  name        = "SG-ALB-3SOAT-G53"
  description = "SG-ALB-3SOAT-G53"
  vpc_id      = aws_vpc.vpc_3soat_g53.id


  ingress {
    protocol    = "tcp"
    from_port   = 8080
    to_port     = 8080
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = merge(local.common_tags, { Name : "ECS-3SOAT-G53-ALB-SG" })
}
