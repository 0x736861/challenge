package org.challenge.eshop.ws

import com.typesafe.config.ConfigFactory

/**
 * Created by Alexander Shurmin.
 */
object Configuration {

  private val conf = ConfigFactory.load()

  val dbDriverClass = conf.getString("db.driverClass")
  val dbJdbcUrl = conf.getString("db.jdbcUrl")
  val dbUser = conf.getString("db.user")
  val dbPassword = conf.getString("db.password")
  val dbMinPoolSize = conf.getInt("db.minPoolSize")
  val dbMaxPoolSize = conf.getInt("db.maxPoolSize")

  val apiVersion = conf.getString("api.version")
}
