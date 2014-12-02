package org.challenge.eshop.ws.to.converter

import org.challenge.eshop.model.Goods
import org.challenge.eshop.ws.to.GoodsTO

/**
 * Created by Alexander Shurmin.
 */
object GoodsTOConverter {

  def toTransferObject(model: Goods): GoodsTO = {
    GoodsTO(
      sku = model.sku,
      name = Option(model.name),
      price = Option(model.price)
    )
  }

  def mergeTransferObjectToModel(to: GoodsTO, model: Goods): Goods = {
    var resultModel = model
    if (to.name.isDefined) resultModel = resultModel.copy(name = to.name.get)
    if (to.price.isDefined) resultModel = resultModel.copy(price = to.price.get)
    resultModel
  }

  def toModel(to: GoodsTO): Goods = {
    Goods(
      sku = to.sku,
      name = to.name.getOrElse(throw new IllegalArgumentException("name")),
      price = to.price.getOrElse(throw new IllegalArgumentException("price"))
    )
  }
}