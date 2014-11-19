package org.challenge.eshop.ws.to.converter

import org.challenge.eshop.model.ProductInfo
import org.challenge.eshop.ws.to.ProductTO

/**
 * Created by Alexander Shurmin.
 */
trait ProductTOConverters {

  implicit class ProductModel2TOConverter(model: ProductInfo) {
    def toTransferObject: ProductTO = {
      ProductTO(
        id = model.id,
        name = Option(model.name),
        price = Option(model.price)
      )
    }
  }

  implicit class ProductTO2ModelConverter(to: ProductTO) {
    def toModel: ProductInfo = {
      ProductInfo(
        id = to.id,
        name = to.name.getOrElse(throw new IllegalArgumentException),
        price = to.price.getOrElse(throw new IllegalArgumentException)
      )
    }
  }

  implicit class ProductModelMergeWithTO(model: ProductInfo) {
    def mergeWith(to: ProductTO): ProductInfo = {
      var resultModel = model
      if (to.name.isDefined) resultModel = resultModel.copy(name = to.name.get)
      if (to.price.isDefined) resultModel = resultModel.copy(price = to.price.get)
      resultModel
    }
  }

}
