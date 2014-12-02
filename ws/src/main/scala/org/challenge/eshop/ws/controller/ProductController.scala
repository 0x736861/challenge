package org.challenge.eshop.ws.controller

import org.challenge.eshop.core.service.ProductService
import org.challenge.eshop.model.Goods
import org.challenge.eshop.ws.to.ProductTO
import org.challenge.eshop.ws.to.converter.ProductTOConverter

/**
 * Created by Alexander Shurmin.
 */
class ProductController(apiVersion: String)(implicit productService: ProductService) extends CRUDController(s"/api/$apiVersion/product") {

  override type ModelType = Goods

  override type TransferObjectType = ProductTO

  override val transferObjectManifest = manifest[ProductTO]

  override val modelService = productService

  override def toTransferObject = ProductTOConverter.toTransferObject

  override def mergeTransferObjectToModel = ProductTOConverter.mergeTransferObjectToModel

  override def toModel = ProductTOConverter.toModel
}
