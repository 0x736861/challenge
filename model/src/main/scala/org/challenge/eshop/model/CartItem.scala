package org.challenge.eshop.model

import org.joda.money.Money

/**
 * Created by Alexander Shurmin.
 */
case class CartItem(
  product: ProductInfo,
  quantity: Double,
  price: Money)
