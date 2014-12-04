package org.challenge.eshop.storage.memory.dao.entity.converter

import org.challenge.eshop.model.Product
import org.challenge.eshop.storage.memory.dao.entity.ProductEntity

/**
 * Created by Alexander Shurmin.
 */
object ProductEntityConverter {

  implicit class ProductEntityConverter(entity: ProductEntity) {
    def toModel: Product = {
      Product(
        id = entity.id,
        sku = entity.sku,
        name = entity.name,
        price = entity.price
      )
    }
  }

  implicit class ProductModelConverter(model: Product) {
    def toEntity: ProductEntity = {
      ProductEntity(
        id = model.id,
        sku = model.sku,
        name = model.name,
        price = model.price
      )
    }
  }

}
