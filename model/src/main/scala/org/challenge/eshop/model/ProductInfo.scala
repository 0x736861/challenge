package org.challenge.eshop.model

import org.joda.money.Money

/**
 * Created by Alexander Shurmin.
 */
case class ProductInfo(
  id: Option[String],
  name: String,
  description: String,
  price: Money)
