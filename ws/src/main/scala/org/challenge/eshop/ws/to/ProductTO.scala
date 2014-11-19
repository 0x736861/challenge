package org.challenge.eshop.ws.to

/**
 * Created by Alexander Shurmin.
 */
case class ProductTO(
  id: Option[String] = None,
  name: String,
  description: Option[String] = None,
  price: Double,
  currencyUnit: String
  )
