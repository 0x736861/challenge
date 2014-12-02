package org.challenge.eshop.ws.controller

import com.twitter.finatra.FinatraServer
import com.twitter.finatra.test.FlatSpecHelper
import org.challenge.eshop.common.converter.JsonConverter._
import org.challenge.eshop.ws.EShopWebServer
import org.challenge.eshop.ws.to.GoodsTO
import org.scalatest.BeforeAndAfter

/**
 * Created by Alexander Shurmin.
 */
class GoodsControllerTest extends FlatSpecHelper with BeforeAndAfter {

  override def server: FinatraServer = EShopWebServer

  implicit def anyToOption[T](value: T): Option[T] = Option[T](value)

  "GET /api/v1/goods?offset=1&limit=2" should "return empty array" in {
    get("/api/v1/goods", Map("offset" -> "1", "limit" -> "2"))

    response.code should equal(200)
    fromJson[List[GoodsTO]](response.body) should equal(List.empty[GoodsTO])
  }

  "POST /api/v1/goods" should "create goods entity" in {
    val goodsTO = GoodsTO(sku = "123", name = "the goods name", price = 123.45)

    post("/api/v1/goods", body = toJson(goodsTO))

    response.code should equal(201)
    fromJson[GoodsTO](response.body) should equal(goodsTO)
  }

  "GET /api/v1/goods/123" should "return Goods by Id" in {
    get("/api/v1/goods/123")

    response.code should equal(200)
    fromJson[GoodsTO](response.body) should equal(GoodsTO("123", "the goods name", 123.45))
  }

  "PUT /api/v1/goods/123" should "returns updated entity" in {
    val goodsTO = GoodsTO(name = "new goods name", price = 999.99)

    put("/api/v1/goods/123", body = toJson(goodsTO))

    response.code should equal(200)

    val updatedTO = fromJson[GoodsTO](response.body)
    updatedTO.name should equal(goodsTO.name)
    updatedTO.price should equal(goodsTO.price)
  }

  "PUT /api/v1/goods/123" should "update entity" in {
    val goodsTO = GoodsTO(name = "name", price = 55.66)

    put("/api/v1/goods/123", body = toJson(goodsTO))
    response.code should equal(200)

    get("/api/v1/goods/123")
    val updatedTO = fromJson[GoodsTO](response.body)
    updatedTO.sku should equal(Some("123"))
    updatedTO.name should equal(goodsTO.name)
    updatedTO.price should equal(goodsTO.price)
  }

  "PUT /api/v1/goods/789" should "create entity" in {
    val goodsTO = GoodsTO(sku = "789", name = "name", price = 55.66)

    put("/api/v1/goods/789", body = toJson(goodsTO))
    response.code should equal(200)

    get("/api/v1/goods/789")
    val updatedTO = fromJson[GoodsTO](response.body)
    updatedTO.sku should equal(Some("789"))
    updatedTO.name should equal(goodsTO.name)
    updatedTO.price should equal(goodsTO.price)
  }

  "DELETE /api/v1/goods/123" should "delete Goods by Id" in {
    delete("/api/v1/goods/123")
    response.code should equal(200)

    delete("/api/v1/goods/123")
    response.code should equal(404)
  }
}

