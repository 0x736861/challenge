package org.challenge.eshop.storage.sql

/**
 * Created by Alexander Shurmin.
 */
case class JdbcSettings(
  driverClass: String,
  jdbcUrl: String,
  user: String,
  password: String,
  minPoolSize: Int,
  maxPoolSize: Int)
