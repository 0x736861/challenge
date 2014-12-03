package org.challenge.eshop.model

/**
 * Created by Alexander Shurmin.
 */
case class Product(
  id: Option[String],
  sku: String,
  name: String,
  price: Double)
