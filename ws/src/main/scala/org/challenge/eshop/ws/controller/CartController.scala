package org.challenge.eshop.ws.controller

import com.twitter.util.Try
import org.challenge.eshop.common.converter.JsonConverter._
import org.challenge.eshop.core.service.CartService
import org.challenge.eshop.ws.exception.ContentParseException
import org.challenge.eshop.ws.to.converter.{CartItemTOConverter, CartTOConverter}
import org.challenge.eshop.ws.to.{CartItemTO, CartTO}
import org.jboss.netty.handler.codec.http.HttpResponseStatus

/**
 * Created by Alexander Shurmin.
 */
class CartController(apiVersion: String)(implicit cartService: CartService) extends BaseController {

  post(s"/api/$apiVersion/cart") { implicit request =>
    val content = contentAsString
    val to = Try(fromJson[CartTO](content)).getOrElse(throw new ContentParseException(content))

    cartService.create(CartTOConverter.toModel(to))
      .map(CartTOConverter.toTransferObject)
      .map(render.status(HttpResponseStatus.CREATED.getCode).json)
  }

  post(s"/api/$apiVersion/cart/:cartId/items") { implicit request =>
    val cartId = routeParam("cartId")
    val content = contentAsString
    val cartItems = Try(fromJson[List[CartItemTO]](content)).getOrElse(throw new ContentParseException(content))


    cartService.addToCart(cartId, cartItems.map(a => CartItemTOConverter.toModel(a)))
      .flatMap(_ => respond(HttpResponseStatus.CREATED))
  }
}
