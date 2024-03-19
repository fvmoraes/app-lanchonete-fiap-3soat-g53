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

variable "container_image" {
  default = "my-app-container"
}
