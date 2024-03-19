resource "aws_vpc" "vpc_3soat_g53" {
  cidr_block           = "192.168.0.0/16"
  enable_dns_support   = true
  enable_dns_hostnames = true

  tags = merge(local.common_tags, { Name : "ECS-3SOAT-G53-VPC" })
}

resource "aws_internet_gateway" "ig_3soat_g53" {
  vpc_id = aws_vpc.vpc_3soat_g53.id
  tags   = merge(local.common_tags, { Name : "ECS-3SOAT-G53-IGW" })
}

resource "aws_subnet" "sbn_3soat_g53" {
  for_each = {
    "pub_a" : ["192.168.1.0/24", "${var.aws_region}a", "Public-A-3SOAT-G53"]
    "pub_b" : ["192.168.2.0/24", "${var.aws_region}b", "Public-B-3SOAT-G53"]
  }

  vpc_id            = aws_vpc.vpc_3soat_g53.id
  cidr_block        = each.value[0]
  availability_zone = each.value[1]

  tags = merge(local.common_tags, { Name = each.value[2] })
}

resource "aws_route_table" "public_3soat_g53" {
  vpc_id = aws_vpc.vpc_3soat_g53.id

  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.ig_3soat_g53.id
  }

  tags = merge(local.common_tags, { Name = "ECS-3SOAT-G53-Public" })
}


resource "aws_route_table_association" "rta_3soat_g53" {
  for_each = local.subnet_ids

  subnet_id      = each.value
  route_table_id = aws_route_table.public_3soat_g53.id
}