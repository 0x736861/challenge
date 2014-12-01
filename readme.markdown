# Shopping cart RESTFul service example


### Runs on port 7070

    sbt
    project ws
    run

### Test an API

#### Create a product

    curl -v -X POST -d '{"name":"product1", "price":11.22}' http://localhost:7070/api/v1/product

    Response
    Status: 201 Created
    Content: {"id":"Ic5kFSODQMOpGfuh","name":"product1","price":11.22}

#### Create an empty cart

    curl -v -X POST -d '{}' http://localhost:7070/api/v1/cart

    Response
    Status: 201 Created
    Content: {"id":"oVjKDqL5kJhr1Ifs"}

#### Add product to cart

     curl -v -X POST -d '[{"sku":SKU, "quantity": QUANTITY}]' http://localhost:7070/api/v1/cart/{cartId}/items

     Response
     Status: 201 Created
     Content:

#### Show cart with items

    curl -v http://localhost:7070/api/v1/cart/{cartid}

    Response
    Status: 200 OK
    Content: {"id":"oVjKDqL5kJhr1Ifs","items":[{"sku":"Ic5kFSODQMOpGfuh","quantity":1.0,"product":{"sku":"Ic5kFSODQMOpGfuh","name":"product1","price":11.22}}]}