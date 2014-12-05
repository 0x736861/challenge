package org.challenge.eshop.model

/**
 * Created by Alexander Shurmin.
 */
case class Cart(
  id: Option[String] = None,
  customer: Customer,
  items: List[CartItem] = List.empty[CartItem])
