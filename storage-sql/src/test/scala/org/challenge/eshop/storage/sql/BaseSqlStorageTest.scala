package org.challenge.eshop.storage.sql

import org.scalatest._

/**
 * Created by Alexander Shurmin.
 */
trait BaseSqlStorageTest extends FunSpec with Matchers with BeforeAndAfter with BeforeAndAfterAll {

  override protected def beforeAll(): Unit = {
    val settings = JdbcSettings(
      driverClass = "org.h2.Driver",
      jdbcUrl = "jdbc:h2:mem:test",
      user = "",
      password = "",
      minPoolSize = 5,
      maxPoolSize = 10
    )

    SqlDbManager.init(settings)
  }

  before({
    SqlDbManager.createSchema()
  })

  after({
    SqlDbManager.dropSchema()
  })

}