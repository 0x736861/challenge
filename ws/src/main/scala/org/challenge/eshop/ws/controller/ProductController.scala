package org.challenge.eshop.ws.controller

import com.twitter.util.Try
import org.challenge.eshop.core.service.ProductService
import org.challenge.eshop.ws.exception.{IncorrectBodyException, IncorrectParameterException, ResourceNotFoundException}
import org.challenge.eshop.ws.to.ProductTO
import org.jboss.netty.handler.codec.http.HttpResponseStatus
import org.jboss.netty.util.CharsetUtil

/**
 * Created by Alexander Shurmin.
 */
class ProductController(apiVersion: String)(implicit productService: ProductService) extends BaseController {

  import org.challenge.eshop.ws.to.converter.ProductTOConverters._
  import org.challenge.eshop.common.converter.JsonConverter._

  get(s"/api/$apiVersion/products/:id") { request =>
    val id = request.routeParams.getOrElse("id", throw new IncorrectParameterException)

    productService.getById(id) map {
      case Some(product) => render.json(product.toTransferObject)
      case _ => throw new ResourceNotFoundException
    }
  }

  get(s"/api/$apiVersion/products") { request =>
    val offset = request.params.getIntOrElse("offset", 0)
    val limit = request.params.getIntOrElse("limit", 10)

    productService.getInRange(offset, limit)
      .map(_.map(_.toTransferObject))
      .map(render.json)
  }

  post(s"/api/$apiVersion/products") { request =>
    val product = Try(fromJson[ProductTO](request.content.toString(CharsetUtil.UTF_8)))
      .getOrElse(throw new IncorrectBodyException)

    productService.create(product.toModel)
      .map(_.toTransferObject)
      .map(render.status(HttpResponseStatus.CREATED.getCode).json)
  }
}
