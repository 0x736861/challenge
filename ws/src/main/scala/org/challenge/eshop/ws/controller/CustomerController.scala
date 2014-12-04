package org.challenge.eshop.ws.controller

import org.challenge.eshop.common.converter.JsonConverter
import org.challenge.eshop.core.service.CustomerService
import org.challenge.eshop.ws.exception.ResourceNotFoundException
import org.challenge.eshop.ws.to.CustomerTO
import org.challenge.eshop.ws.to.converter.CustomerTOConverter._
import org.jboss.netty.handler.codec.http.HttpResponseStatus

/**
 * Created by Alexander Shurmin.
 */
class CustomerController(apiVersion: String)(implicit customerService: CustomerService) extends BaseController {

  val baseUrl = s"/api/$apiVersion/customers"

  /**
   * Get Customers in range from offset with limit
   */
  get(baseUrl) { implicit request =>
    val offset = request.params.getIntOrElse("offset", 0)
    val limit = request.params.getIntOrElse("limit", 10)

    customerService.get(offset, limit)
      .map(_.map(_.toTransferObject))
      .map(render.json)
  }

  /**
   * Get Customer by Id
   */
  get(s"$baseUrl/:id") { implicit request =>
    val id = routeParam("id")

    customerService.get(id) map {
      case Some(model) => render.body(JsonConverter.toJson(model.toTransferObject))
      case _ => throw new ResourceNotFoundException
    }
  }

  /**
   * Create new Customer and assign to it new Id
   */
  post(baseUrl) { implicit request =>
    val to = fromContent[CustomerTO]

    customerService.create(to.toModel.copy())
      .map(_.toTransferObject)
      .map(render.status(HttpResponseStatus.CREATED.getCode).json)
  }

  /**
   * Update existed Customer by Id
   */
  post(s"$baseUrl/:id") { implicit request =>
    val id = routeParam("id")
    val to = fromContent[CustomerTO]

    customerService.get(id).flatMap {
      case Some(model) => customerService.update(model.updateFrom(to)).map(render.json)
      case _ => throw new ResourceNotFoundException
    }
  }

  /**
   * Create or update Customer by Id
   */
  put(s"$baseUrl/:id") { implicit request =>
    val id = routeParam("id")
    val to = fromContent[CustomerTO]

    customerService.get(id).flatMap {
      case Some(model) =>
        val mergedModel = model.updateFrom(to)
        customerService.update(mergedModel).map(_ => render.json(mergedModel))
      case _ =>
        val modelWithId = to.toModel.copy(id = Some(id))
        customerService.create(modelWithId).map(render.json)
    }
  }

  /**
   * Delete Customer by Id
   */
  delete(s"$baseUrl/:id") { implicit request =>
    val id = routeParam("id")

    customerService.delete(id) flatMap {
      case true => respond(HttpResponseStatus.OK)
      case false => throw new ResourceNotFoundException
    }
  }
}
