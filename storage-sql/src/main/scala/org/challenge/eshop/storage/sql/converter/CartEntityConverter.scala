package org.challenge.eshop.storage.sql.converter

import org.challenge.eshop.model.Cart
import org.challenge.eshop.storage.sql.schema.entity.CartEntity


/**
 * Created by Alexander Shurmin.
 */
object CartEntityConverter {

  def toEntity(model: Cart): CartEntity = {
    CartEntity(
      id = model.id.getOrElse("")
    )
  }

  def toModel(entity: CartEntity): Cart = {
    Cart(
      id = Some(entity.id))
  }

  def toOptionModel(entity: Option[CartEntity]): Option[Cart] = {
    entity.map(toModel)
  }

}
