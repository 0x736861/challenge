package org.challenge.eshop.ws

import com.twitter.finatra.FinatraServer
import org.challenge.eshop.ws.controller.GoodsController

/**
 * Created by Alexander Shurmin.
 */
object EShopWebServer extends FinatraServer {

  initStorage()

  registerControllers()

  fillDemoDatabase()

  private def initStorage(): Unit = {
    //    val settings = JdbcSettings(
    //      driverClass = Configuration.dbDriverClass,
    //      jdbcUrl = Configuration.dbJdbcUrl,
    //      user = Configuration.dbUser,
    //      password = Configuration.dbPassword,
    //      minPoolSize = Configuration.dbMinPoolSize,
    //      maxPoolSize = Configuration.dbMaxPoolSize
    //    )
    //
    //    SqlDbManager.init(settings)
    //    SqlDbManager.createSchema()
  }

  private def registerControllers() {
    import org.challenge.eshop.ws.Services._

    val apiVersion = Configuration.apiVersion

    register(new GoodsController(apiVersion))
    //register(new CartController(apiVersion))
  }

  private def fillDemoDatabase(): Unit = {
    //    Await.ready(Future.join(
    //      productService.create(Goods(name = "product1", price = 11.22)),
    //      productService.create(Goods(name = "product2", price = 33.44))
    //    ))
  }
}
