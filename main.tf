provider "aws" {
  alias                   = "localstack"
  region                  = "us-east-1"
  access_key = "test"
  secret_key = "test"
  skip_credentials_validation = true
  skip_metadata_api_check     = true
  skip_requesting_account_id  = true
  endpoints {
    dynamodb = "http://localhost:4566" # Use the LocalStack endpoint
  }
}

resource "aws_dynamodb_table" "receipt_table" {
  provider = aws.localstack  # Use the LocalStack-specific provider alias
  name           = "Receipt"
  billing_mode   = "PAY_PER_REQUEST"
  hash_key       = "Id"

  attribute {
    name = "Id"
    type = "S"
  }

  attribute {
    name = "UserId"
    type = "S"
  }

  attribute {
    name = "Path"
    type = "S"
  }

  global_secondary_index {
      name               = "UserIdIndex"
      hash_key           = "UserId"
      range_key          = "Path"
      projection_type    = "ALL"
  }
}
