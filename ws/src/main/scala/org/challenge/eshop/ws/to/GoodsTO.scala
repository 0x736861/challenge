package org.challenge.eshop.ws.to

/**
 * Created by Alexander Shurmin.
 */
case class GoodsTO(
  sku: Option[String] = None,
  name: Option[String] = None,
  price: Option[Double] = None)
