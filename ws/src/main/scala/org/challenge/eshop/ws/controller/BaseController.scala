package org.challenge.eshop.ws.controller

import com.twitter.finatra.{Request, Controller}
import com.twitter.util.Try
import org.challenge.eshop.ws.exception.{ContentParseException, IncorrectContentException, IncorrectParameterException, ResourceNotFoundException}
import org.jboss.netty.handler.codec.http.HttpResponseStatus
import org.jboss.netty.util.CharsetUtil

/**
 * Created by Alexander Shurmin.
 */
trait BaseController extends Controller {

  error { request =>
    request.error match {
      case Some(e: ResourceNotFoundException) => respond(HttpResponseStatus.NOT_FOUND)
      case Some(_: IncorrectParameterException) => respond(HttpResponseStatus.BAD_REQUEST)
      case Some(_: IncorrectContentException) => respond(HttpResponseStatus.BAD_REQUEST)
      case Some(_: ContentParseException) => respond(HttpResponseStatus.BAD_REQUEST)

      case _ => respond(HttpResponseStatus.INTERNAL_SERVER_ERROR)
    }
  }

  def respond(status: HttpResponseStatus) = {
    render.status(status.getCode).header("Content-Type", "application/json").toFuture
  }

  notFound { request =>
    render.status(404).plain("not found yo").toFuture
  }

  def contentAsString(implicit request: Request): String = {
    Try(request.content.toString(CharsetUtil.UTF_8)).getOrElse(throw new IncorrectContentException)
  }

  def routeParam(name: String)(implicit request: Request): String = {
    request.routeParams.getOrElse(name, throw new IncorrectParameterException)
  }

}
