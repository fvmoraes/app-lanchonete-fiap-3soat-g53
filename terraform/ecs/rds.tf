# RDS
resource "aws_security_group" "rds" {
  name        = "RDS-SG-3SOAT-G53"
  description = "SG-RDS-3SOAT-G53"
  vpc_id      = aws_vpc.vpc_3soat_g53.id

  ingress {
    from_port   = var.database_port
    to_port     = var.database_port
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
    Name = "RDS-SG-3SOAT-G53"
  }
}

resource "aws_db_subnet_group" "rds-sbg" {
  name       = "rds-sbg"
  subnet_ids = [aws_subnet.sbn_3soat_g53["pub_a"].id, aws_subnet.sbn_3soat_g53["pub_b"].id]

  tags = {
    Name = "RDS-SBG-3SOAT-G53"
  }
}

resource "aws_db_instance" "rds-db-3soat-g53" {
  identifier           = "rds-db-3soat-g53"
  allocated_storage    = 10
  storage_type         = "gp2"
  engine               = "postgres"
  engine_version       = "15.5"
  instance_class       = "db.t3.micro"
  db_name              = var.database_name
  username             = var.database_user
  password             = var.database_password
  publicly_accessible  = true
  skip_final_snapshot  = false
  vpc_security_group_ids = [aws_security_group.rds.id]
  db_subnet_group_name = aws_db_subnet_group.rds-sbg.name
  
  tags = {
    Name = "RDS-DB-3SOAT-G53"
  }
}
