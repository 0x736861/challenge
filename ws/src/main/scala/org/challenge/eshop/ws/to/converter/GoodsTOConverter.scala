package org.challenge.eshop.ws.to.converter

import org.challenge.eshop.model.Product
import org.challenge.eshop.ws.to.ProductTO

/**
 * Created by Alexander Shurmin.
 */
object GoodsTOConverter {

  implicit class GoodsModelConverter(model: Product) {
    def toTransferObject: ProductTO = {
      ProductTO(
        sku = model.sku,
        name = Option(model.name),
        price = Option(model.price)
      )
    }

    def updateFrom(to: ProductTO): Product = {
      var resultModel = model
      if (to.name.isDefined) resultModel = resultModel.copy(name = to.name.get)
      if (to.price.isDefined) resultModel = resultModel.copy(price = to.price.get)
      resultModel
    }
  }

  implicit class GoodsTOConverter(to: ProductTO) {
    def toModel: Product = {
      Product(
        sku = to.sku,
        name = to.name.getOrElse(throw new IllegalArgumentException("name")),
        price = to.price.getOrElse(throw new IllegalArgumentException("price"))
      )
    }
  }

}
