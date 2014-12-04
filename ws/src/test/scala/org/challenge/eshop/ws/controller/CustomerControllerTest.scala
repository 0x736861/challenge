package org.challenge.eshop.ws.controller

import com.twitter.finatra.FinatraServer
import com.twitter.finatra.test.FlatSpecHelper
import org.challenge.eshop.common.converter.JsonConverter._
import org.challenge.eshop.ws.EShopWebServer
import org.challenge.eshop.ws.to.CustomerTO
import org.scalatest.BeforeAndAfter

/**
 * Created by Alexander Shurmin.
 */
class CustomerControllerTest extends FlatSpecHelper with BeforeAndAfter {

  override def server: FinatraServer = EShopWebServer

  implicit def anyToOption[T](value: T): Option[T] = Option[T](value)

  "GET /api/v1/customers?offset=1&limit=2" should "return empty array" in {
    get("/api/v1/customers", Map("offset" -> "1", "limit" -> "2"))

    response.code should equal(200)
    fromJson[List[CustomerTO]](response.body) should equal(List.empty[CustomerTO])
  }

  "POST /api/v1/customers" should "create customer entity and assign to it new Id" in {
    val customerTO = CustomerTO(name = "the customer name")
    post("/api/v1/customers", body = toJson(customerTO))

    response.code should equal(201)
    val createdCustomer = fromJson[CustomerTO](response.body)
    createdCustomer.id.isDefined should equal(true)
    customerTO.name should equal(createdCustomer.name)
  }

  "POST /api/v1/customers/:id" should "update customer by Id" in {
    val customerTO = CustomerTO(name = "name 4")
    post("/api/v1/customers", body = toJson(customerTO))
    val createdCustomer = fromJson[CustomerTO](response.body)

    val changedCustomerTO = CustomerTO(name = "newName")
    post(s"/api/v1/customers/${createdCustomer.id.get}", body = toJson(changedCustomerTO))
    val updatedCustomer = fromJson[CustomerTO](response.body)

    createdCustomer.id should equal(updatedCustomer.id)
    changedCustomerTO.name should equal(updatedCustomer.name)
  }

  "POST /api/v1/customers/:id" should "return 404 for non existed Customer" in {
    val customerTO = CustomerTO(name = "name 4")
    post(s"/api/v1/customers/-1", body = toJson(customerTO))
    response.code should equal(404)
  }

  "GET /api/v1/customers/:id" should "return customer by Id" in {
    val customerTO = CustomerTO(name = "34234")
    post("/api/v1/customers", body = toJson(customerTO))
    val createdCustomer = fromJson[CustomerTO](response.body)

    get(s"/api/v1/customers/${createdCustomer.id.get}")
    response.code should equal(200)
    val requestedCustomer = fromJson[CustomerTO](response.body)
    createdCustomer.name should equal(requestedCustomer.name)
  }

  "GET /api/v1/customers/:id" should "return 404 for non existed Customer" in {
    get(s"/api/v1/customers/-1")
    response.code should equal(404)
  }

  "PUT /api/v1/customers/:id" should "create new Customer" in {
    val customerTO = CustomerTO(name = "new customer name")
    put("/api/v1/customers/8858", body = toJson(customerTO))

    response.code should equal(200)
    val createdCustomer = fromJson[CustomerTO](response.body)
    createdCustomer.id.isDefined shouldEqual true
    customerTO.name should equal(createdCustomer.name)
  }

  "PUT /api/v1/customers/:id" should "update existed Customer" in {
    val customerTO = CustomerTO(name = "asdfasdf")
    post("/api/v1/customers", body = toJson(customerTO))
    val createdCustomer = fromJson[CustomerTO](response.body)

    val changedCustomer = createdCustomer.copy(name = "333")
    put(s"/api/v1/customers/${createdCustomer.id.get}", body = toJson(changedCustomer))
    response.code should equal(200)
    val updatedTO = fromJson[CustomerTO](response.body)
    changedCustomer.name should equal(updatedTO.name)

    get(s"/api/v1/customers/${createdCustomer.id.get}")
    val requestedTO = fromJson[CustomerTO](response.body)
    changedCustomer.name should equal(requestedTO.name)
  }

  "DELETE /api/v1/customers/:id" should "delete existed Customer" in {
    val customerTO = CustomerTO(name = "asdfasdf")
    post("/api/v1/customers", body = toJson(customerTO))
    val createdCustomer = fromJson[CustomerTO](response.body)

    delete(s"/api/v1/customers/${createdCustomer.id.get}")
    response.code should equal(200)
  }

  "DELETE /api/v1/customers/:id" should "return 404 for non existed Customer" in {
    delete("/api/v1/customers/-1")
    response.code should equal(404)
  }
}

