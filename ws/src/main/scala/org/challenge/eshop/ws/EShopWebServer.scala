package org.challenge.eshop.ws

import com.twitter.finatra.FinatraServer
import org.challenge.eshop.ws.controller.{CustomerController, ProductController}

/**
 * Created by Alexander Shurmin.
 */
object EShopWebServer extends FinatraServer {

  registerControllers()

  private def registerControllers() {
    import org.challenge.eshop.ws.Services._

    val apiVersion = Configuration.apiVersion

    register(new ProductController(apiVersion))
    register(new CustomerController(apiVersion))
  }

}
