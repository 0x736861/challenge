package org.challenge.eshop.ws.to.converter

import org.challenge.eshop.model.ProductInfo
import org.challenge.eshop.ws.to.ProductTO

/**
 * Created by Alexander Shurmin.
 */
object ProductTOConverter {

  def toTransferObject(model: ProductInfo): ProductTO = {
    ProductTO(
      id = model.sku,
      name = Option(model.name),
      price = Option(model.price)
    )
  }

  def mergeTransferObjectToModel(to: ProductTO, model: ProductInfo): ProductInfo = {
    var resultModel = model
    if (to.name.isDefined) resultModel = resultModel.copy(name = to.name.get)
    if (to.price.isDefined) resultModel = resultModel.copy(price = to.price.get)
    resultModel
  }

  def toModel(to: ProductTO): ProductInfo = {
    ProductInfo(
      sku = to.id,
      name = to.name.getOrElse(throw new IllegalArgumentException),
      price = to.price.getOrElse(throw new IllegalArgumentException)
    )
  }
}
