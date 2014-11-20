package org.challenge.eshop.ws.controller

import com.twitter.finatra.Controller
import org.challenge.eshop.core.service.TimeService

/**
 * Created by Alexander Shurmin.
 */
class TimeController extends Controller {

  get("/") { request =>
    render.plain(TimeService.getTime).toFuture
  }

}
