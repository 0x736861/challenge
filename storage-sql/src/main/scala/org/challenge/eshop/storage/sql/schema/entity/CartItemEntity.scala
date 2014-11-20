package org.challenge.eshop.storage.sql.schema.entity

import org.squeryl.KeyedEntity
import org.squeryl.dsl.CompositeKey2

/**
 * Created by Alexander Shurmin.
 */
case class CartItemEntity(
  cardId: String,
  sku: String,
  quantity: Double) extends KeyedEntity[CompositeKey2[String, String]] {

  def id = CompositeKey2(cardId, sku)
}
