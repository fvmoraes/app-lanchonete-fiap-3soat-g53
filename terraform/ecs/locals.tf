locals {

  subnet_ids = { for k, v in aws_subnet.this : v.tags.Name => v.id }

  common_tags = {
    Project   = "ECS Fargate"
    CreatedAt = "2022-12-17"
    ManagedBy = "3SOAT G53"
    Owner     = "Franklin Moraes"
    Service   = "ECS Fargate"
  }
}