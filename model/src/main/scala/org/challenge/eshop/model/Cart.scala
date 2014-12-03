package org.challenge.eshop.model

/**
 * Created by Alexander Shurmin.
 */
case class Cart(
  id: Option[String],
  items: Option[List[CartItem]] = None) extends Entity[String]
