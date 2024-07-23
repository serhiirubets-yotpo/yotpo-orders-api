# serhii-orders-api

## Requirements
Java SDK 21


## How to run:

## Run build with gradle:
./gradlew build

## Build docker image:
docker build --no-cache .

## Run with docker:
docker-compose up

Server will be available at http://localhost:8080


## For test run:

GET http://localhost:8080/orders/stores/1

with body
```json
{"orderDate":"2024-07-15T17:55:42.896Z",
  "shopperEmail":"test@gmail.com",
  "shopperFirstName":"Test 1111",
  "shopperLastName":"Test 1111",

  "products":[{
    "externalProductId": "1111",
    "productName": "test-product-name",
    "productDescription": "test-product-description",
    "productPrice": 45,
    "quantity": 2
  }]}
```

It will create 3 new records in DB:
- Orders
- Products
- OrderProducts