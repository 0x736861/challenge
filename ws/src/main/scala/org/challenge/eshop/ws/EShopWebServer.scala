package org.challenge.eshop.ws

import com.twitter.finatra.FinatraServer
import com.twitter.util.{Future, Await}
import com.typesafe.config.ConfigFactory
import org.challenge.eshop.core.service.ProductService
import org.challenge.eshop.model.ProductInfo
import org.challenge.eshop.storage.api.EntityManagerFactory
import org.challenge.eshop.storage.sql.{SqlDbManager, JdbcSettings, SqlEntityManagerFactory}
import org.challenge.eshop.ws.controller.{ProductController, TimeController}
import org.joda.money.{CurrencyUnit, Money}

/**
 * Created by Alexander Shurmin.
 */
object EShopWebServer extends FinatraServer {

  import Services._

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
  }

  private def fillDemoDatabase(): Unit = {
    Await.ready(Future.join(
      productService.create(ProductInfo(name = "product1", price = Money.of(CurrencyUnit.of("USD"), 11.22))),
      productService.create(ProductInfo(name = "product2", price = Money.of(CurrencyUnit.of("USD"), 33.44)))
    ))
  }
}
