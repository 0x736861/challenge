package org.challenge.eshop.ws.controller

import com.twitter.finatra.{Controller, UnsupportedMediaType}
import org.challenge.eshop.ws.exception.{IncorrectBodyException, IncorrectParameterException, ResourceNotFoundException}
import org.jboss.netty.handler.codec.http.HttpResponseStatus

/**
 * Created by Alexander Shurmin.
 */
trait BaseController extends Controller {

  error { request =>
    request.error match {
      case Some(e: ResourceNotFoundException) =>
        render.status(HttpResponseStatus.NOT_FOUND.getCode).header("Content-Type", "application/json").toFuture
      case Some(_: IncorrectParameterException) | Some(_: IncorrectBodyException) =>
        render.status(HttpResponseStatus.BAD_REQUEST.getCode).header("Content-Type", "application/json").toFuture
      case Some(e: UnsupportedMediaType) =>
        render.status(415).plain("Unsupported Media Type!").toFuture
      case _ =>
        render.status(500).plain("Something went wrong!").toFuture
    }
  }

  /**
   * Custom 404s
   *
   * curl http://localhost:7070/notfound
   */
  notFound { request =>
    render.status(404).plain("not found yo").toFuture
  }
}
