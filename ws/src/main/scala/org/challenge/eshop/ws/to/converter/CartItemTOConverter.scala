package org.challenge.eshop.ws.to.converter

import org.challenge.eshop.model.CartItem
import org.challenge.eshop.ws.to.CartItemTO

/**
 * Created by Alexander Shurmin.
 */
object CartItemTOConverter {

  def toTransferObject(model: CartItem): CartItemTO = {
    CartItemTO(
      sku = model.sku,
      quantity = model.quantity
    )
  }

  def toModel(to: CartItemTO): CartItem = {
    CartItem(
      sku = to.sku,
      quantity = to.quantity
    )
  }
}
