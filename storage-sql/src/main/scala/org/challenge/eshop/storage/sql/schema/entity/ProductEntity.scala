package org.challenge.eshop.storage.sql.schema.entity

import org.squeryl.KeyedEntity
import org.squeryl.annotations.Column

/**
 * Created by Alexander Shurmin.
 */
case class ProductEntity(

  id: String,

  @Column(length = 512)
  name: String,

  description: Option[String] = None,

  price: BigDecimal,

  @Column(length = 64)
  currencyUnit: String

  ) extends KeyedEntity[String]
