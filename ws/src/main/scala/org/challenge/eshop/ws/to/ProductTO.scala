package org.challenge.eshop.ws.to

/**
 * Created by Alexander Shurmin.
 */
case class ProductTO(
  id: Option[String] = None,
  name: Option[String] = None,
  price: Option[Double] = None
  )
