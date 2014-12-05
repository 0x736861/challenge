package org.challenge.eshop.model

/**
 * Created by Alexander Shurmin.
 */
case class CartItem(
  id: Option[String] = None,
  sku: String,
  quantity: Double,
  position: Option[Int] = None)
