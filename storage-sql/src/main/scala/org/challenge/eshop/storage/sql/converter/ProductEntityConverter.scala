package org.challenge.eshop.storage.sql.converter

import org.challenge.eshop.model.ProductInfo
import org.challenge.eshop.storage.sql.schema.entity.ProductEntity


/**
 * Created by Alexander Shurmin.
 */
object ProductEntityConverter {

  implicit class Model2Entity(model: ProductInfo) {
    def toEntity: ProductEntity = {
      ProductEntity(
        id = model.sku.getOrElse(""),
        name = model.name,
        price = model.price
      )
    }
  }

  implicit class Entity2Model(entity: ProductEntity) {
    def toModel: ProductInfo = {
      ProductInfo(
        sku = Some(entity.id),
        name = entity.name,
        price = entity.price.doubleValue()
      )
    }
  }

  implicit class OptionEntity2Model(entity: Option[ProductEntity]) {
    def toModel: Option[ProductInfo] = {
      entity.map(_.toModel)
    }
  }

}
