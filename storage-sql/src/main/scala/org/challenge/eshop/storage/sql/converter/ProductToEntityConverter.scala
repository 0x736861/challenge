package org.challenge.eshop.storage.sql.converter

import org.challenge.eshop.common.converter.ScalaToJavaTypeConverters
import org.challenge.eshop.model.ProductInfo
import org.challenge.eshop.storage.sql.schema.entity.ProductEntity
import org.joda.money.{CurrencyUnit, Money}


/**
 * Created by Alexander Shurmin.
 */
object ProductToEntityConverter {

  import ScalaToJavaTypeConverters._

  implicit def modelToEntity(model: ProductInfo): ProductEntity = {
    ProductEntity(
      id = model.id.getOrElse(""),
      name = model.name,
      description = model.description,
      price = model.price.getAmount,
      currencyUnit = model.price.getCurrencyUnit.getCode
    )
  }

  implicit def entityToModel(entity: ProductEntity): ProductInfo = {
    val currency = CurrencyUnit.of(entity.currencyUnit)
    ProductInfo(
      id = Some(entity.id),
      name = entity.name,
      description = entity.description,
      price = Money.of(currency, entity.price)
    )
  }

  implicit def optionEtityToModel(entity: Option[ProductEntity]): Option[ProductInfo] = {
    entity.map(modelToEntity(_))
  }
}
