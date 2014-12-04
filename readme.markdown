# Shopping cart RESTFul service example


## Runs on port 7070

    sbt
    project ws
    run

## Test an API

### Products

#### Create a product

    curl -v -X POST -d '{"sku": "123", "name":"product1", "price":11.22}' http://localhost:7070/api/v1/products

    Response
    Status: 201 Created
    Content: {"id":"U728oFAXZRi4h6ax","sku":"123","name":"product1","price":11.22}

#### Get product by Id

    curl -v http://localhost:7070/api/v1/products/U728oFAXZRi4h6ax

    Response
    Status: 200 OK
    Content: {"id":"U728oFAXZRi4h6ax","sku":"123","name":"product1","price":11.22}

### Customers

#### Create a customer

    curl -v -X POST -d '{"name":"customer1"}' http://localhost:7070/api/v1/customers

    Response
    Status: 201 Created
    Content: {"id":"cXta0jrAwL1ur0s6","name":"customer1"}

#### Get customer by Id

    curl -v http://localhost:7070/api/v1/customers/cXta0jrAwL1ur0s6

    Response
    Status: 200 OK
    Content: {"id":"cXta0jrAwL1ur0s6","name":"customer1"}
