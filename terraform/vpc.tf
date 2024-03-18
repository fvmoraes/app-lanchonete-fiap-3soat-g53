provider "aws" {
  region = "us-east-1"
}

resource "aws_vpc" "app_lanchonete_fiap_3soat_g53_main" {
  cidr_block = "10.0.0.0/16"
  enable_dns_hostnames = true
  tags = {
    Name = "app_lanchonete_fiap_3soat_g53_vpc"
  }
}

resource "aws_subnet" "app_lanchonete_fiap_3soat_g53_subnet1" {
  vpc_id            = aws_vpc.app_lanchonete_fiap_3soat_g53_main.id
  cidr_block        = "10.0.1.0/24"
  availability_zone = "us-east-1a"
  tags = {
    Name = "app_lanchonete_fiap_3soat_g53_subnet1"
  }
}

resource "aws_subnet" "app_lanchonete_fiap_3soat_g53_subnet2" {
  vpc_id            = aws_vpc.app_lanchonete_fiap_3soat_g53_main.id
  cidr_block        = "10.0.2.0/24"
  availability_zone = "us-east-1b"
    tags = {
    Name = "app_lanchonete_fiap_3soat_g53_subnet2"
  }
}

resource "aws_internet_gateway" "app_lanchonete_fiap_3soat_g53_igw" {
  vpc_id = aws_vpc.app_lanchonete_fiap_3soat_g53_main.id
      tags = {
    Name = "app_lanchonete_fiap_3soat_g53_igw"
  }
}


resource "aws_route_table" "app_lanchonete_fiap_3soat_g53_main_route_table" {
  vpc_id = aws_vpc.app_lanchonete_fiap_3soat_g53_main.id
  tags = {
    Name = "app-lanchonete-fiap-3soat-g53-main-route-table"
  }
}