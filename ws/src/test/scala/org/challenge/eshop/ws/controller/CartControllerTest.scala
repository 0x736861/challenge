package org.challenge.eshop.ws.controller

import com.twitter.finatra.FinatraServer
import com.twitter.finatra.test.FlatSpecHelper
import org.challenge.eshop.ws.EShopWebServer
import org.challenge.eshop.ws.to.{CartItemTO, GoodsTO, CartTO}
import org.challenge.eshop.common.converter.JsonConverter._

/**
 * Created by Alexander Shurmin.
 */
class CartControllerTest extends FlatSpecHelper {

  override def server: FinatraServer = EShopWebServer

  "POST /api/v1/cart" should "create an empty cart" in {
    val cartTO = CartTO()

    post("/api/v1/cart", body = toJson(cartTO))

    response.code should equal(201)
    response.body.length should not equal 0

    val resultTO = fromJson[CartTO](response.body)
    resultTO.id.isDefined should equal(true)
    resultTO.id.get.isEmpty should equal(false)
  }

  "POST /api/v1/cart/cartId/items" should "add items to cart" in {
    val product1TO = GoodsTO(name = Some("product1"), price = Some(100.0))
    post("/api/v1/product", body = toJson(product1TO))
    val product1Id = fromJson[GoodsTO](response.body).sku.getOrElse(throw new Exception("ProductId is not specified"))

    val product2TO = GoodsTO(name = Some("product2"), price = Some(200.0))
    post("/api/v1/product", body = toJson(product2TO))
    val product2Id = fromJson[GoodsTO](response.body).sku.getOrElse(throw new Exception("ProductId is not specified"))

    val cartTO = CartTO()
    post("/api/v1/cart", body = toJson(cartTO))
    val cartId = fromJson[CartTO](response.body).id.get

    val itemsTO = List(CartItemTO(product1Id, 10.0), CartItemTO(product2Id, 20.0))
    post(s"/api/v1/cart/$cartId/items", body = toJson(itemsTO))
    response.code should equal(201)

    get(s"/api/v1/cart/$cartId")
    println(response.body)
  }
}
