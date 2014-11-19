package org.challenge.eshop.ws.to.converter

import org.challenge.eshop.model.ProductInfo
import org.challenge.eshop.ws.to.ProductTO
import org.joda.money.{CurrencyUnit, Money}

/**
 * Created by Alexander Shurmin.
 */
object ProductTOConverters {

  implicit class ProductModel2TOConverter(model: ProductInfo) {
    def toTransferObject: ProductTO = {
      ProductTO(
        id = model.id,
        name = model.name,
        description = model.description,
        price = model.price.getAmount.doubleValue(),
        currencyUnit = model.price.getCurrencyUnit.getCode
      )
    }
  }

  implicit class ProductTO2ModelConverter(to: ProductTO) {
    def toModel: ProductInfo = {
      ProductInfo(
        id = to.id,
        name = to.name,
        description = to.description,
        price = Money.of(CurrencyUnit.of(to.currencyUnit), to.price)
      )
    }
  }
}
