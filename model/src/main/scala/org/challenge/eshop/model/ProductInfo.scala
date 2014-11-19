package org.challenge.eshop.model

/**
 * Created by Alexander Shurmin.
 */
case class ProductInfo(
  id: Option[String] = None,
  name: String,
  price: Double)
