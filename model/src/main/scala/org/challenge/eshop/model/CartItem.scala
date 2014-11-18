package org.challenge.eshop.model

import org.joda.money.Money

/**
 * Created by Alexander Shurmin.
 */
case class CartItem(
  product: Product,
  quantity: Double,
  price: Money)
