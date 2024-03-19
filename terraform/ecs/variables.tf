variable "aws_access_key" {
  type        = string
  description = "use env aws keys"
}

variable "aws_secret_key" {
  type        = string
  description = "use env aws keys"
}

variable "aws_region" {
  default = "us-east-1"
}

# ECS
variable "desired_capacity" {
  description = "desired number of running nodes"
  default     = 1
}

variable "container_port" {
  default = "8080"
}

variable "image_url" {
  default = "fvmoraes/applanchonetetok8s:latest"
}

variable "memory" {
  default = "2048"
}

variable "cpu" {
  default = "1024"
}

variable "cluster_name" {
  default = "3soat-g53-cluster"
}

variable "cluster_task" {
  default = "3soat-g53-task"
}
variable "cluster_service" {
  default = "3soat-g53-service"
}

# RDS
variable "database_port" {
  default = "5432"
}

variable "database_user" {
  default = "jnnwlymy"
}

variable "database_password" {
  default = "dt0MGNj3kg7Ie5t6-C48oY6A43ewIcRI"
}

variable "database_name" {
  default = "jnnwlymy"
}

variable "database_url" {
  default = "jdbc:postgresql://drona.db.elephantsql.com:5432/jnnwlymy"
}

# variable "database_url" {
#   default = "jdbc:postgresql://${aws_db_instance.rds-db-3soat-g53.endpoint}/fiap"
# }


# ECS TEST
variable "desired_capacity_test" {
  description = "desired number of running nodes"
  default     = 1
}

variable "container_port_test" {
  default = "3000"
}

variable "image_url_test" {
  default = "fvmoraes/dev-chat:latest"
}

variable "memory_test" {
  default = "512"
}

variable "cpu_test" {
  default = "256"
}

variable "cluster_name_test" {
  default = "3soat-g53-cluster"
}

variable "cluster_task_test" {
  default = "3soat-g53-task-test"
}
variable "cluster_service_test" {
  default = "3soat-g53-service-test"
}
