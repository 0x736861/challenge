package org.challenge.eshop.storage.sql.converter

import org.challenge.eshop.model.Cart
import org.challenge.eshop.storage.sql.schema.entity.CartEntity


/**
 * Created by Alexander Shurmin.
 */
object CartEntityConverter {

  implicit class Model2Entity(model: Cart) {
    def toEntity: CartEntity = {
      CartEntity(
        id = model.id.getOrElse("")
      )
    }
  }

  implicit class Entity2Model(entity: CartEntity) {
    def toModel: Cart = {
      Cart(
        id = Some(entity.id))
    }
  }

  implicit class OptionEntity2Model(entity: Option[CartEntity]) {
    def toModel: Option[Cart] = {
      entity.map(_.toModel)
    }
  }

}
