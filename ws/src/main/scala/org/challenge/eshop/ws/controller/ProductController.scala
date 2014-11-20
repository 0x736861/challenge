package org.challenge.eshop.ws.controller

import org.challenge.eshop.core.service.ProductService
import org.challenge.eshop.model.ProductInfo
import org.challenge.eshop.ws.to.ProductTO
import org.challenge.eshop.ws.to.converter.ProductTOConverter

/**
 * Created by Alexander Shurmin.
 */
class ProductController(apiVersion: String)(implicit productService: ProductService) extends BaseController(apiVersion) {

  override type ModelType = ProductInfo

  override type TransferObjectType = ProductTO

  override implicit val transferObjectManifest = manifest[ProductTO]

  override val modelService = productService

  override val converter = ProductTOConverter
}