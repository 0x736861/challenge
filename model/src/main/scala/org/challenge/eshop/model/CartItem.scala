package org.challenge.eshop.model


/**
 * Created by Alexander Shurmin.
 */
case class CartItem(
  id: Option[String] = None,
  sku: String,
  quantity: Double,
  product: Option[Product] = None) extends Entity[String] {
}
