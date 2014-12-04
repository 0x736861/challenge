package org.challenge.eshop.ws

import com.typesafe.config.ConfigFactory

/**
 * Created by Alexander Shurmin.
 */
object Configuration {

  private val conf = ConfigFactory.load()

  val apiVersion = conf.getString("api.version")
}
