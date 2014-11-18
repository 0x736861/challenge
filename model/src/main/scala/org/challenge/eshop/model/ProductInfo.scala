package org.challenge.eshop.model

import org.joda.money.Money

/**
 * Created by Alexander Shurmin.
 */
case class ProductInfo(
  id: Option[String] = None,
  name: String,
  description: Option[String] = None,
  price: Money)
