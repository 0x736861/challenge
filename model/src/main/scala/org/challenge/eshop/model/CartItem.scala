package org.challenge.eshop.model


/**
 * Created by Alexander Shurmin.
 */
case class CartItem(
  sku: String,
  quantity: Double,
  product: Option[ProductInfo] = None)
