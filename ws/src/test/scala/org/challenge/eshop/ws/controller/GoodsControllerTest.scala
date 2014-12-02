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
}
