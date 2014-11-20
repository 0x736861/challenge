package org.challenge.eshop.ws.to.converter

import org.challenge.eshop.model.Cart
import org.challenge.eshop.ws.to.CartTO

/**
 * Created by Alexander Shurmin.
 */
object CartTOConverter {

  def toTransferObject(model: Cart): CartTO = {
    CartTO(
      id = model.id
    )
  }

  def toModel(to: CartTO): Cart = {
    Cart(
      id = to.id
    )
  }
}
