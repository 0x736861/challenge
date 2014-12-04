# Shopping cart RESTFul service example


### Runs on port 7070

    sbt
    project ws
    run

### Test an API

#### Create a product

    curl -v -X POST -d '{"sku": "123", "name":"product1", "price":11.22}' http://localhost:7070/api/v1/products

    Response
    Status: 201 Created
    Content: {"id":"U728oFAXZRi4h6ax","sku":"123","name":"product1","price":11.22}

#### Get product details

    curl -v http://localhost:7070/api/v1/products/U728oFAXZRi4h6ax

    Response
    Status: 200 OK
    Content: {"id":"U728oFAXZRi4h6ax","sku":"123","name":"product1","price":11.22}
