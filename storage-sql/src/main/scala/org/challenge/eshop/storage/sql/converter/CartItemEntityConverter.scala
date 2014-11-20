package org.challenge.eshop.storage.sql.converter

import org.challenge.eshop.model.CartItem
import org.challenge.eshop.storage.sql.schema.entity.CartItemEntity

/**
 * Created by Alexander Shurmin.
 */
object CartItemEntityConverter {

  def toEntity(cartId: String, model: CartItem): CartItemEntity = {
    CartItemEntity(
      cardId = cartId,
      sku = model.sku,
      quantity = model.quantity)
  }

  def toModel(entity: CartItemEntity): CartItem = {
    CartItem(
      sku = entity.sku,
      quantity = entity.quantity)
  }
}
