package org.challenge.eshop.model

/**
 * Created by Alexander Shurmin.
 */
case class ProductInfo(
  sku: Option[String] = None,
  name: String,
  price: Double)
