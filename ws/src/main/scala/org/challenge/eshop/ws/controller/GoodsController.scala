package org.challenge.eshop.ws.controller

import com.twitter.util.Try
import org.challenge.eshop.common.converter.JsonConverter._
import org.challenge.eshop.core.service.GoodsService
import org.challenge.eshop.ws.exception.{ContentParseException, ResourceNotFoundException}
import org.challenge.eshop.ws.to.GoodsTO
import org.challenge.eshop.ws.to.converter.GoodsTOConverter._
import org.jboss.netty.handler.codec.http.HttpResponseStatus

/**
 * Created by Alexander Shurmin.
 */
class GoodsController(apiVersion: String)(implicit goodsService: GoodsService) extends BaseController {

  val baseUrl = s"/api/$apiVersion/goods"

  get(baseUrl) { implicit request =>
    val offset = request.params.getIntOrElse("offset", 0)
    val limit = request.params.getIntOrElse("limit", 10)

    goodsService.get(offset, limit)
      .map(_.map(toTransferObject))
      .map(render.json)
  }

  get(s"$baseUrl/:id") { implicit request =>
    val id = routeParam("id")

    goodsService.get(id) map {
      case Some(model) => render.json(toTransferObject(model))
      case _ => throw new ResourceNotFoundException
    }
  }

  post(baseUrl) { implicit request =>
    val content = contentAsString
    val to = Try(fromJson[GoodsTO](content)).getOrElse(throw new ContentParseException(content))

    goodsService.create(toModel(to))
      .map(toTransferObject)
      .map(render.status(HttpResponseStatus.CREATED.getCode).json)
  }
}
