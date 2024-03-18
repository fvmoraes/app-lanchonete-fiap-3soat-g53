resource "aws_db_subnet_group" "app_lanchonete_fiap_3soat_g53_subnet_group" {
  name        = "app_lanchonete_fiap_3soat_g53_db_subnet_group"
  subnet_ids  = [
    aws_subnet.app_lanchonete_fiap_3soat_g53_subnet1.id,
    aws_subnet.app_lanchonete_fiap_3soat_g53_subnet2.id
  ]  
  tags = {
    Name = "app_lanchonete_fiap_3soat_g53_db_subnet_group"
  }
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
