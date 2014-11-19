package org.challenge.eshop.ws.controller

import com.twitter.finatra.FinatraServer
import com.twitter.finatra.test.FlatSpecHelper
import org.challenge.eshop.ws.EShopWebServer

/**
 * Created by Alexander Shurmin.
 */
class ProductControllerTest extends FlatSpecHelper {

  override def server: FinatraServer = EShopWebServer

  "GET /api/v1/products" should "respond 200" in {
    get("/api/v1/products")
    response.code should equal(200)
    response.body.length shouldNot equal(0)
  }
}
