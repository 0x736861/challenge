package org.challenge.eshop.ws.controller

import com.twitter.util.Try
import org.challenge.eshop.common.converter.JsonConverter._
import org.challenge.eshop.core.service.BaseService
import org.challenge.eshop.ws.exception.{ContentParseException, IncorrectContentException, IncorrectParameterException, ResourceNotFoundException}
import org.challenge.eshop.ws.to.converter.TOConverter
import org.jboss.netty.handler.codec.http.HttpResponseStatus

/**
 * Created by Alexander Shurmin.
 */
abstract class CRUDController(baseUrlPath: String) extends BaseController {

  type ModelType

  type TransferObjectType

  implicit val transferObjectManifest: Manifest[TransferObjectType]

  val modelService: BaseService[ModelType]

  val converter: TOConverter[TransferObjectType, ModelType]

  import converter._

  get(s"$baseUrlPath/:id") { request =>
    val id = request.routeParams.getOrElse("id", throw new IncorrectParameterException)

    modelService.getById(id) map {
      case Some(model) => render.json(toTransferObject(model))
      case _ => throw new ResourceNotFoundException
    }
  }

  get(baseUrlPath) { request =>
    val offset = request.params.getIntOrElse("offset", 0)
    val limit = request.params.getIntOrElse("limit", 10)

    modelService.getInRange(offset, limit)
      .map(_.map(toTransferObject))
      .map(render.json)
  }

  post(baseUrlPath) { implicit request =>
    val content = contentAsString
    val to = Try(fromJson[TransferObjectType](contentAsString)).getOrElse(throw new ContentParseException(content))

    modelService.create(toModel(to))
      .map(toTransferObject)
      .map(render.status(HttpResponseStatus.CREATED.getCode).json)
  }

  post(s"$baseUrlPath/:id") { implicit request =>
    val id = request.routeParams.getOrElse("id", throw new IncorrectParameterException)
    val content = contentAsString
    val to = Try(fromJson[TransferObjectType](content)).getOrElse(throw new ContentParseException(content))

    modelService.getById(id).flatMap {
      case Some(model) =>
        val mergedProModel = mergeTransferObjectToModel(to, model)
        modelService.update(mergedProModel).map(_ => render.json(mergedProModel))

      case _ => throw new ResourceNotFoundException
    }
  }

  put(s"$baseUrlPath/:id") { implicit request =>
    val id = request.routeParams.getOrElse("id", throw new IncorrectParameterException)
    val content = contentAsString
    val to = Try(fromJson[TransferObjectType](content)).getOrElse(throw new IncorrectContentException)

    modelService.getById(id).flatMap {
      case Some(model) =>
        val mergedModel = mergeTransferObjectToModel(to, model)
        modelService.update(mergedModel).map(_ => render.json(mergedModel))
      case _ =>
        modelService.create(toModel(to), Some(id)).map(render.json)
    }
  }

  delete(s"$baseUrlPath/:id") { request =>
    val id = request.routeParams.getOrElse("id", throw new IncorrectParameterException)
    modelService.delete(id) flatMap {
      case true => respond(HttpResponseStatus.OK)
      case false => throw new ResourceNotFoundException
    }
  }

}
