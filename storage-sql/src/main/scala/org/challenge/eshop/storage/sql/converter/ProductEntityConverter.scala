package org.challenge.eshop.storage.sql.converter

import org.challenge.eshop.model.ProductInfo
import org.challenge.eshop.storage.sql.schema.entity.ProductEntity


/**
 * Created by Alexander Shurmin.
 */
object ProductEntityConverter {

  implicit class ProductModel2Entity(model: ProductInfo) {
    def toEntity: ProductEntity = {
      ProductEntity(
        id = model.id.getOrElse(""),
        name = model.name,
        price = model.price
      )
    }
  }

  implicit class ProductEntity2Model(entity: ProductEntity) {
    def toModel: ProductInfo = {
      ProductInfo(
        id = Some(entity.id),
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
