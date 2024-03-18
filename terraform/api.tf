resource "aws_api_gateway_rest_api" "app_lanchonete_fiap_3soat_g53_api" {
  name        = "app_lanchonete_fiap_3soat_g53_api"
  description = "3SOATG53 API Gateway"
  tags = {
    Name = "app_lanchonete_fiap_3soat_g53_api"
  }
}

resource "aws_cognito_user_pool" "app_lanchonete_fiap_3soat_g53_user_pool" {
  name = "app_lanchonete_fiap_3soat_g53_user_pool"
  tags = {
    Name = "app_lanchonete_fiap_3soat_g53_user_pool"
  }
}

resource "aws_cognito_user_pool_client" "app_lanchonete_fiap_3soat_g53_user_pool_client" {
  name                   = "app_lanchonete_fiap_3soat_g53_user_pool_client"
  user_pool_id           = aws_cognito_user_pool.app_lanchonete_fiap_3soat_g53_user_pool.id
  generate_secret        = false
  explicit_auth_flows    = ["ADMIN_NO_SRP_AUTH"]
  allowed_oauth_flows    = ["code"]
  allowed_oauth_scopes   = ["openid"]
  callback_urls          = ["http://localhost"]
  logout_urls            = ["http://localhost"]
  supported_identity_providers = ["COGNITO"]
}

output "api_gateway_invoke_url" {
  value = aws_api_gateway_rest_api.app_lanchonete_fiap_3soat_g53_api.root_resource_id
}
