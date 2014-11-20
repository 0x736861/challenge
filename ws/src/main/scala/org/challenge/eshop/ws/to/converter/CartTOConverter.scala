package org.challenge.eshop.ws.to.converter

import org.challenge.eshop.model.Cart
import org.challenge.eshop.ws.to.CartTO

/**
 * Created by Alexander Shurmin.
 */
object CartTOConverter extends TOConverter[CartTO, Cart] {

  override def toTransferObject(model: Cart): CartTO = {
    CartTO(
      id = model.id
    )
  }

  override def mergeTransferObjectToModel(to: CartTO, model: Cart): Cart = {
    var resultModel = model
    resultModel
  }

  override def toModel(to: CartTO): Cart = {
    Cart(
      id = to.id
    )
  }
}
