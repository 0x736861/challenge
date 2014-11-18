package org.challenge.eshop.ws

import com.twitter.finatra.FinatraServer
import org.challenge.eshop.ws.controller.TimeController

/**
 * Created by Alexander Shurmin.
 */
object EShopWebServer extends FinatraServer {

  register(new TimeController)
}
