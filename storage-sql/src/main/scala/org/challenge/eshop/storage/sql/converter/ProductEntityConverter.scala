package org.challenge.eshop.storage.sql.converter

import org.challenge.eshop.model.Goods
import org.challenge.eshop.storage.sql.schema.entity.ProductEntity


/**
 * Created by Alexander Shurmin.
 */
object ProductEntityConverter {

  implicit class Model2Entity(model: Goods) {
    def toEntity: ProductEntity = {
      ProductEntity(
        id = model.sku.getOrElse(""),
        name = model.name,
        price = model.price
      )
    }
  }

  implicit class Entity2Model(entity: ProductEntity) {
    def toModel: Goods = {
      Goods(
        sku = Some(entity.id),
        name = entity.name,
        price = entity.price.doubleValue()
      )
    }
  }

  implicit class OptionEntity2Model(entity: Option[ProductEntity]) {
    def toModel: Option[Goods] = {
      entity.map(_.toModel)
    }
  }

}
