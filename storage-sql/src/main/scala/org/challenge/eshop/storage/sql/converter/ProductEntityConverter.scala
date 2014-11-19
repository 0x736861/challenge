package org.challenge.eshop.storage.sql.converter

import org.challenge.eshop.common.converter.ScalaToJavaTypeConverters
import org.challenge.eshop.model.ProductInfo
import org.challenge.eshop.storage.sql.schema.entity.ProductEntity
import org.joda.money.{CurrencyUnit, Money}


/**
 * Created by Alexander Shurmin.
 */
object ProductEntityConverter {

  import ScalaToJavaTypeConverters._

  implicit class ProductModel2Entity(model: ProductInfo) {
    def toEntity: ProductEntity = {
      ProductEntity(
        id = model.id.getOrElse(""),
        name = model.name,
        description = model.description,
        price = model.price.getAmount,
        currencyUnit = model.price.getCurrencyUnit.getCode
      )
    }
  }

  implicit class ProductEntity2Model(entity: ProductEntity) {
    def toModel: ProductInfo = {
      val currency = CurrencyUnit.of(entity.currencyUnit)
      ProductInfo(
        id = Some(entity.id),
        name = entity.name,
        description = entity.description,
        price = Money.of(currency, entity.price)
      )
    }
  }

  implicit class OptionEntity2Model(entity: Option[ProductEntity]) {
    def toModel: Option[ProductInfo] = {
      entity.map(_.toModel)
    }
  }

}
