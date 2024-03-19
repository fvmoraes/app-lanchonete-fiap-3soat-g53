resource "aws_lb" "this" {
  name               = "ECS-3SOAT-G53-ALB"
  security_groups    = [aws_security_group.alb.id]
  load_balancer_type = "application"

  subnets = [aws_subnet.this["pub_a"].id, aws_subnet.this["pub_b"].id]

  tags = merge(local.common_tags, { Name = "ECS 3SOAT-G53 ALB" })

}

resource "aws_lb_target_group" "this" {
  name        = "ALB-TG-3SOAT-G53"
  port        = 8080
  protocol    = "HTTP"
  target_type = "ip"
  vpc_id      = aws_vpc.this.id

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

resource "aws_lb_listener" "this" {
  load_balancer_arn = aws_lb.this.arn
  port              = 8080
  protocol          = "HTTP"

  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.this.arn
  }
}

resource "aws_security_group" "alb" {
  name        = "ECS-3SOAT-G53-ALB-SG"
  description = "SG-ALB-3SOAT-G53"
  vpc_id      = aws_vpc.this.id


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

  tags = merge(local.common_tags, { Name : "ECS 3SOAT-G53 ALB-SG" })
}

# ALB TEST
resource "aws_lb" "lb-test" {
  name               = "ECS-3SOAT-G53-ALB-TEST"
  security_groups    = [aws_security_group.alb_test.id]
  load_balancer_type = "application"

  subnets = [aws_subnet.this["pub_a"].id, aws_subnet.this["pub_b"].id]

  tags = merge(local.common_tags, { Name = "ECS 3SOAT-G53 ALB-TEST" })

}

resource "aws_lb_target_group" "alb_tg_test" {
  name        = "ALB-TEST-TG-3SOAT-G53"
  port        = 3000
  protocol    = "HTTP"
  target_type = "ip"
  vpc_id      = aws_vpc.this.id

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

resource "aws_lb_listener" "alb_listener_test" {
  load_balancer_arn = aws_lb.lb-test.arn
  port              = 3000
  protocol          = "HTTP"

  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.alb_tg_test.arn
  }
}

resource "aws_security_group" "alb_test" {
  name        = "ECS-3SOAT-G53-ALB-TEST-SG"
  description = "SG-ALB-TEST-3SOAT-G53"
  vpc_id      = aws_vpc.this.id


  ingress {
    protocol    = "tcp"
    from_port   = 3000
    to_port     = 3000
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = merge(local.common_tags, { Name : "ECS 3SOAT-G53 ALB-TEST-SG" })
}

# ALB RDS
resource "aws_lb" "lb-rds" {
  name               = "ECS-3SOAT-G53-ALB-RDS"
  security_groups    = [aws_security_group.alb_rds.id]
  load_balancer_type = "application"

  subnets = [aws_subnet.this["pub_a"].id, aws_subnet.this["pub_b"].id]

  tags = merge(local.common_tags, { Name = "ECS 3SOAT-G53 ALB-RDS" })

}

resource "aws_lb_target_group" "alb_tg_rds" {
  name        = "ALB-RDS-TG-3SOAT-G53"
  port        = 5432
  protocol    = "HTTP"
  target_type = "ip"
  vpc_id      = aws_vpc.this.id

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

resource "aws_lb_listener" "alb_listener_rds" {
  load_balancer_arn = aws_lb.lb-rds.arn
  port              = 5432
  protocol          = "HTTP"

  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.alb_tg_rds.arn
  }
}

resource "aws_security_group" "alb_rds" {
  name        = "ECS-3SOAT-G53-ALB-RDS-SG"
  description = "SG-ALB-RDS-3SOAT-G53"
  vpc_id      = aws_vpc.this.id


  ingress {
    protocol    = "tcp"
    from_port   = 5432
    to_port     = 5432
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = merge(local.common_tags, { Name : "ECS 3SOAT-G53 ALB-TEST-SG" })
}
