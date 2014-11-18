package org.challenge.eshop.ws.controller

import org.challenge.eshop.core.service.TimeService

/**
 * Created by Alexander Shurmin.
 */
class TimeController extends BaseController {

  get("/") { request =>
    render.plain(TimeService.getTime).toFuture
  }

}
