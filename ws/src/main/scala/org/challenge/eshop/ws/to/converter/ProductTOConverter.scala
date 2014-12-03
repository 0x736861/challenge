package org.challenge.eshop.ws.to.converter

import org.challenge.eshop.model.Product
import org.challenge.eshop.ws.to.ProductTO

/**
 * Created by Alexander Shurmin.
 */
object ProductTOConverter {

  implicit class ProductModelConverter(model: Product) {
    def toTransferObject: ProductTO = {
      ProductTO(
        id = model.id,
        sku = Option(model.sku),
        name = Option(model.name),
        price = Option(model.price)
      )
    }

    def updateFrom(to: ProductTO): Product = {
      var resultModel = model
      resultModel = resultModel.copy(id = to.id)
      if (to.sku.isDefined) resultModel = resultModel.copy(sku = to.sku.get)
      if (to.name.isDefined) resultModel = resultModel.copy(name = to.name.get)
      if (to.price.isDefined) resultModel = resultModel.copy(price = to.price.get)
      resultModel
    }
  }

  implicit class ProductTOConverter(to: ProductTO) {
    def toModel: Product = {
      Product(
        id = to.id,
        sku = to.sku.getOrElse(throw new IllegalArgumentException("sku")),
        name = to.name.getOrElse(throw new IllegalArgumentException("name")),
        price = to.price.getOrElse(throw new IllegalArgumentException("price"))
      )
    }
  }

}
