package org.challenge.eshop.ws.controller

import com.twitter.util.Try
import org.challenge.eshop.common.converter.JsonConverter._
import org.challenge.eshop.core.service.CartService
import org.challenge.eshop.ws.exception.ContentParseException
import org.challenge.eshop.ws.to.CartTO
import org.challenge.eshop.ws.to.converter.CartTOConverter._
import org.jboss.netty.handler.codec.http.HttpResponseStatus

/**
 * Created by Alexander Shurmin.
 */
class CartController(apiVersion: String)(implicit cartService: CartService) extends BaseController {

  post(s"/api/$apiVersion/carts") { implicit request =>
    val content = contentAsString
    val to = Try(fromJson[CartTO](content)).getOrElse(throw new ContentParseException(content))

    cartService.create(toModel(to))
      .map(toTransferObject)
      .map(render.status(HttpResponseStatus.CREATED.getCode).json)
  }
}
