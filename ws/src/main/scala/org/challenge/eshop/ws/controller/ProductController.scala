package org.challenge.eshop.ws.controller

import com.twitter.util.Try
import org.challenge.eshop.core.service.ProductService
import org.challenge.eshop.ws.exception.{ContentParseException, IncorrectContentException, IncorrectParameterException, ResourceNotFoundException}
import org.challenge.eshop.ws.to.ProductTO
import org.jboss.netty.handler.codec.http.HttpResponseStatus
import org.jboss.netty.util.CharsetUtil

/**
 * Created by Alexander Shurmin.
 */
class ProductController(apiVersion: String)(implicit productService: ProductService) extends BaseController {

  import org.challenge.eshop.ws.to.converter.TOConverters._
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
    val content = Try(request.content.toString(CharsetUtil.UTF_8)).getOrElse(throw new IncorrectContentException)
    val productTO = Try(fromJson[ProductTO](content)).getOrElse(throw new ContentParseException(content))

    productService.create(productTO.toModel)
      .map(_.toTransferObject)
      .map(render.status(HttpResponseStatus.CREATED.getCode).json)
  }

  post(s"/api/$apiVersion/products/:id") { request =>
    val id = request.routeParams.getOrElse("id", throw new IncorrectParameterException)
    val content = Try(request.content.toString(CharsetUtil.UTF_8)).getOrElse(throw new IncorrectContentException)
    val productTO = Try(fromJson[ProductTO](content)).getOrElse(throw new ContentParseException(content))

    productService.getById(id).flatMap {
      case Some(product) =>
        val mergedProduct = product.mergeWith(productTO)
        productService.update(mergedProduct).map(_ => render.json(mergedProduct))

      case _ => throw new ResourceNotFoundException
    }
  }

  put(s"/api/$apiVersion/products/:id") { request =>
    val id = request.routeParams.getOrElse("id", throw new IncorrectParameterException)
    val productTO = Try(fromJson[ProductTO](request.content.toString(CharsetUtil.UTF_8)))
      .getOrElse(throw new IncorrectContentException)

    productService.getById(id).flatMap {
      case Some(product) =>
        val mergedProduct = product.mergeWith(productTO)
        productService.update(mergedProduct).map(_ => render.json(mergedProduct))
      case _ =>
        val newProduct = productTO.toModel.copy(id = Some(id))
        productService.create(newProduct).map(render.json)
    }
  }

  delete(s"/api/$apiVersion/products/:id") { request =>
    val id = request.routeParams.getOrElse("id", throw new IncorrectParameterException)
    productService.delete(id) flatMap {
      case true => respond(HttpResponseStatus.OK)
      case false => throw new ResourceNotFoundException
    }
  }
}
