resource "aws_security_group" "app_lanchonete_fiap_3soat_g53_sg" {
  name        = "app-lanchonete-fiap-3soat-g53-sg"
  description = "Security group for the ALB"
  vpc_id      = aws_vpc.app_lanchonete_fiap_3soat_g53_main.id

  ingress {
    from_port   = 3000
    to_port     = 3000
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "app-lanchonete-fiap-3soat-g53-sg"
  }
}
