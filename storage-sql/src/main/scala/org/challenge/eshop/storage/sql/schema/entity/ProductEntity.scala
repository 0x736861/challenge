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

  price: BigDecimal

  ) extends KeyedEntity[String]
