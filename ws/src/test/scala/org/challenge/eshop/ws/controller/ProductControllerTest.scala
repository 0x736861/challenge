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
    createdProduct.id.isDefined shouldBe true
    compare(productTO, createdProduct)
  }

  //  "POST /api/v1/products/999" should "create product entity by Id" in {
  //    val productTO = ProductTO(name = "the product name", price = 123.45)
  //
  //    post("/api/v1/products/999", body = toJson(productTO))
  //
  //    response.code should equal(200)
  //    fromJson[ProductTO](response.body) should equal(productTO.copy(sku = Some("999")))
  //  }
  //
  //  "GET /api/v1/products/:id" should "return product by Id" in {
  //    val sku = Some("444")
  //    val name = Some("name name")
  //    val price = Some(122.75)
  //
  //    val productTO = ProductTO(sku = sku, name = name, price = price)
  //    post("/api/v1/products/77", body = toJson(productTO))
  //    val createdProduct = fromJson[ProductTO](response.body)
  //
  //    get("/api/v1/products/" + createdProduct.id.get)
  //    response.code should equal(200)
  //    val requestedProduct = fromJson[ProductTO](response.body)
  //    requestedProduct.sku should equal(sku)
  //    requestedProduct.name should equal(name)
  //    requestedProduct.price should equal(price)
  //    println(response.body)
  //  }
  //
  //  "PUT /api/v1/products/123" should "returns updated entity" in {
  //    val productTO = ProductTO(name = "new product name", price = 999.99)
  //
  //    put("/api/v1/products/123", body = toJson(productTO))
  //
  //    response.code should equal(200)
  //
  //    val updatedTO = fromJson[ProductTO](response.body)
  //    updatedTO.name should equal(productTO.name)
  //    updatedTO.price should equal(productTO.price)
  //  }
  //
  //  "PUT /api/v1/products/123" should "update entity" in {
  //    val productTO = ProductTO(name = "name", price = 55.66)
  //
  //    put("/api/v1/products/123", body = toJson(productTO))
  //    response.code should equal(200)
  //
  //    get("/api/v1/products/123")
  //    val updatedTO = fromJson[ProductTO](response.body)
  //    updatedTO.sku should equal(Some("123"))
  //    updatedTO.name should equal(productTO.name)
  //    updatedTO.price should equal(productTO.price)
  //  }
  //
  //  "PUT /api/v1/products/789" should "create entity" in {
  //    val productTO = ProductTO(sku = "789", name = "name", price = 55.66)
  //
  //    put("/api/v1/products/789", body = toJson(productTO))
  //    response.code should equal(200)
  //
  //    get("/api/v1/products/789")
  //    val updatedTO = fromJson[ProductTO](response.body)
  //    updatedTO.sku should equal(Some("789"))
  //    updatedTO.name should equal(productTO.name)
  //    updatedTO.price should equal(productTO.price)
  //  }
  //
  //  "DELETE /api/v1/products/123" should "delete Goods by Id" in {
  //    delete("/api/v1/products/123")
  //    response.code should equal(200)
  //
  //    delete("/api/v1/products/123")
  //    response.code should equal(404)
  //  }

  def compare(a: ProductTO, b: ProductTO, compareId: Boolean = false): Unit = {
    a.sku should equal(b.sku)
    a.name should equal(b.name)
    a.price should equal(b.price)
  }
}

