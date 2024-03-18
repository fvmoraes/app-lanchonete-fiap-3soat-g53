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
  default = "512"
}

variable "cpu" {
  default = "256"
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
