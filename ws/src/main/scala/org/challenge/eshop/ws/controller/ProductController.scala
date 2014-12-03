package org.challenge.eshop.ws.controller

import org.challenge.eshop.common.converter.JsonConverter
import org.challenge.eshop.core.service.ProductService
import org.challenge.eshop.ws.exception.ResourceNotFoundException
import org.challenge.eshop.ws.to.ProductTO
import org.challenge.eshop.ws.to.converter.ProductTOConverter._
import org.jboss.netty.handler.codec.http.HttpResponseStatus

/**
 * Created by Alexander Shurmin.
 */
class ProductController(apiVersion: String)(implicit productService: ProductService) extends BaseController {

  val baseUrl = s"/api/$apiVersion/products"

  /**
   * Get Products in range from offset with limit
   */
  get(baseUrl) { implicit request =>
    val offset = request.params.getIntOrElse("offset", 0)
    val limit = request.params.getIntOrElse("limit", 10)

    productService.get(offset, limit)
      .map(_.map(_.toTransferObject))
      .map(render.json)
  }

  /**
   * Get Product by Id
   */
  get(s"$baseUrl/:id") { implicit request =>
    val id = routeParam("id")

    productService.get(id) map {
      case Some(model) => render.body(JsonConverter.toJson(model.toTransferObject))
      case _ => throw new ResourceNotFoundException
    }
  }

  /**
   * Create new Product and assign to it new Id
   */
  post(baseUrl) { implicit request =>
    val to = fromContent[ProductTO]

    productService.create(to.toModel.copy())
      .map(_.toTransferObject)
      .map(render.status(HttpResponseStatus.CREATED.getCode).json)
  }

  /**
   * Create new Product by Id
   */
  post(s"$baseUrl/:id") { implicit request =>
    val id = routeParam("id")
    val to = fromContent[ProductTO]

    productService.create(to.toModel.copy(id = Some(id)))
      .map(_.toTransferObject)
      .map(render.status(HttpResponseStatus.OK.getCode).json)
  }

  /**
   * Create or update Product by Id
   */
  put(s"$baseUrl/:id") { implicit request =>
    val id = routeParam("id")
    val to = fromContent[ProductTO]

    productService.get(id).flatMap {
      case Some(model) =>
        val mergedModel = model.updateFrom(to)
        productService.update(mergedModel).map(_ => render.json(mergedModel))
      case _ =>
        val modelWithId = to.toModel.copy(id = Some(id))
        productService.create(modelWithId).map(render.json)
    }
  }

  /**
   * Delete Product by Id
   */
  delete(s"$baseUrl/:id") { implicit request =>
    val id = routeParam("id")

    productService.delete(id) flatMap {
      case true => respond(HttpResponseStatus.OK)
      case false => throw new ResourceNotFoundException
    }
  }
}
