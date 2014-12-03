package org.challenge.eshop.storage.memory.dao.entity

import org.challenge.eshop.storage.api.dao.entity.BaseEntity

/**
 * Created by Alexander Shurmin.
 */
case class ProductEntity(
  var id: Option[String],
  sku: String,
  name: String,
  price: Double) extends BaseEntity[String]