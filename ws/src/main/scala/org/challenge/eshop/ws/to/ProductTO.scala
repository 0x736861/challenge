package org.challenge.eshop.ws.to

/**
 * Created by Alexander Shurmin.
 */
case class ProductTO(
  sku: Option[String] = None,
  name: Option[String] = None,
  price: Option[Double] = None)
