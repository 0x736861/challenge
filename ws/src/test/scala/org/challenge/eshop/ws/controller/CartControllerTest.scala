package org.challenge.eshop.ws.controller

import com.twitter.finatra.FinatraServer
import com.twitter.finatra.test.FlatSpecHelper
import org.challenge.eshop.ws.EShopWebServer
import org.challenge.eshop.ws.to.{ProductTO, CartItemTO, CartTO}
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
    response.body.length shouldNot equal(0)

    val resultTO = fromJson[CartTO](response.body)
    resultTO.id.isDefined should equal(true)
    resultTO.id.get.isEmpty should equal(false)
  }

  "POST /api/v1/cart/cartId/items" should "add items to cart" in {
    val productTO = ProductTO(name = Some("product1"), price = Some(100.0))
    post("/api/v1/product", body = toJson(productTO))
    val productId = fromJson[ProductTO](response.body).id.getOrElse(throw new Exception("ProductId is not specified"))

    val cartTO = CartTO()
    post("/api/v1/cart", body = toJson(cartTO))
    val createdCartTO = fromJson[CartTO](response.body)

    val itemsTO = List(CartItemTO(productId, 10.0))
    post(s"/api/v1/cart/${createdCartTO.id.get}/items", body = toJson(itemsTO))

    response.code should equal(201)
  }
}
