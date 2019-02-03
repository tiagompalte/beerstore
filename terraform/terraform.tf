terraform {
  backend "s3" {
    bucket = "terraform-state-tiagompaltemail"
    key = "beerstore-curso-online"
    region = "us-east-2"
    profile = "terraform"
  }
}