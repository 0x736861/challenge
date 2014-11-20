package org.challenge.eshop.ws

import com.twitter.finatra.FinatraServer
import com.twitter.util.{Await, Future}
import org.challenge.eshop.model.ProductInfo
import org.challenge.eshop.storage.sql.{JdbcSettings, SqlDbManager}
import org.challenge.eshop.ws.controller.{CartController, ProductController, TimeController}

/**
 * Created by Alexander Shurmin.
 */
object EShopWebServer extends FinatraServer {

  import org.challenge.eshop.ws.Services._

  initStorage()

  registerControllers()

  fillDemoDatabase()

  private def initStorage(): Unit = {
    val settings = JdbcSettings(
      driverClass = Configuration.dbDriverClass,
      jdbcUrl = Configuration.dbJdbcUrl,
      user = Configuration.dbUser,
      password = Configuration.dbPassword,
      minPoolSize = Configuration.dbMinPoolSize,
      maxPoolSize = Configuration.dbMaxPoolSize
    )

    SqlDbManager.init(settings)
    SqlDbManager.createSchema()
  }

  private def registerControllers() {
    val apiVersion = Configuration.apiVersion

    register(new TimeController)
    register(new ProductController(apiVersion))
    register(new CartController(apiVersion))
  }

  private def fillDemoDatabase(): Unit = {
    Await.ready(Future.join(
      productService.create(ProductInfo(name = "product1", price = 11.22)),
      productService.create(ProductInfo(name = "product2", price = 33.44))
    ))
  }
}
