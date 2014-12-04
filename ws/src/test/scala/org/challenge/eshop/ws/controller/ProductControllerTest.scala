package org.challenge.eshop.ws.controller

import com.twitter.finatra.FinatraServer
import com.twitter.finatra.test.FlatSpecHelper
import org.challenge.eshop.common.converter.JsonConverter._
import org.challenge.eshop.ws.EShopWebServer
import org.challenge.eshop.ws.to.ProductTO
import org.scalatest.BeforeAndAfter

/**
 * Created by Alexander Shurmin.
 */
class ProductControllerTest extends FlatSpecHelper with BeforeAndAfter {

  override def server: FinatraServer = EShopWebServer

  implicit def anyToOption[T](value: T): Option[T] = Option[T](value)

  "GET /api/v1/products?offset=1&limit=2" should "return empty array" in {
    get("/api/v1/products", Map("offset" -> "1", "limit" -> "2"))

    response.code should equal(200)
    fromJson[List[ProductTO]](response.body) should equal(List.empty[ProductTO])
  }

  "POST /api/v1/products" should "create product entity and assign to it new Id" in {
    val productTO = ProductTO(sku = "123", name = "the product name", price = 123.45)
    post("/api/v1/products", body = toJson(productTO))

    response.code should equal(201)
    val createdProduct = fromJson[ProductTO](response.body)
    createdProduct.id.isDefined should equal(true)
    compare(productTO, createdProduct)
  }

  "POST /api/v1/products/:id" should "update product by Id" in {
    val productTO = ProductTO(sku = "1234", name = "name 4", price = 123.45)
    post("/api/v1/products", body = toJson(productTO))
    val createdProduct = fromJson[ProductTO](response.body)

    val changedProductTO = ProductTO(name = "newName", price = 99.5)
    post(s"/api/v1/products/${createdProduct.id.get}", body = toJson(changedProductTO))
    val updatedProduct = fromJson[ProductTO](response.body)

    createdProduct.id should equal(updatedProduct.id)
    createdProduct.sku should equal(updatedProduct.sku)
    changedProductTO.name should equal(updatedProduct.name)
    changedProductTO.price should equal(updatedProduct.price)
  }

  "POST /api/v1/products/:id" should "return 404 for non existed Product" in {
    val productTO = ProductTO(sku = "1234", name = "name 4", price = 123.45)
    post(s"/api/v1/products/-1", body = toJson(productTO))
    response.code should equal(404)
  }

  "GET /api/v1/products/:id" should "return product by Id" in {
    val productTO = ProductTO(sku = "23423", name = "34234", price = 123.4)
    post("/api/v1/products", body = toJson(productTO))
    val createdProduct = fromJson[ProductTO](response.body)

    get(s"/api/v1/products/${createdProduct.id.get}")
    response.code should equal(200)
    val requestedProduct = fromJson[ProductTO](response.body)
    compare(createdProduct, requestedProduct)
  }

  "GET /api/v1/products/:id" should "return 404 for non existed Product" in {
    get(s"/api/v1/products/-1")
    response.code should equal(404)
  }

  "PUT /api/v1/products/:id" should "create new Product" in {
    val productTO = ProductTO(sku = "234", name = "new product name", price = 999.99)
    put("/api/v1/products/8858", body = toJson(productTO))

    response.code should equal(200)
    val createdProduct = fromJson[ProductTO](response.body)
    createdProduct.id.isDefined shouldEqual true
    compare(productTO, createdProduct)
  }

  "PUT /api/v1/products/:id" should "update existed Product" in {
    val productTO = ProductTO(sku = "fdsdf", name = "asdfasdf", price = 1.4)
    post("/api/v1/products", body = toJson(productTO))
    val createdProduct = fromJson[ProductTO](response.body)

    val changedProduct = createdProduct.copy(sku = "w3e").copy(name = "333").copy(price = 22.3)
    put(s"/api/v1/products/${createdProduct.id.get}", body = toJson(changedProduct))
    response.code should equal(200)
    val updatedTO = fromJson[ProductTO](response.body)
    compare(changedProduct, updatedTO)

    get(s"/api/v1/products/${createdProduct.id.get}")
    val requestedTO = fromJson[ProductTO](response.body)
    compare(changedProduct, requestedTO)
  }

  "DELETE /api/v1/products/:id" should "delete existed Product" in {
    val productTO = ProductTO(sku = "fdsdf", name = "asdfasdf", price = 1.4)
    post("/api/v1/products", body = toJson(productTO))
    val createdProduct = fromJson[ProductTO](response.body)

    delete(s"/api/v1/products/${createdProduct.id.get}")
    response.code should equal(200)
  }

  "DELETE /api/v1/products/:id" should "return 404 for non existed Product" in {
    delete("/api/v1/products/-1")
    response.code should equal(404)
  }

  def compare(a: ProductTO, b: ProductTO, compareId: Boolean = false): Unit = {
    a.sku should equal(b.sku)
    a.name should equal(b.name)
    a.price should equal(b.price)
  }
}

