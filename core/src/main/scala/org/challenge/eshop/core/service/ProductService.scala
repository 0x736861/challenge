package org.challenge.eshop.core.service

import com.twitter.util.Future
import org.challenge.eshop.model.Product
import org.challenge.eshop.storage.api.manager.ProductManager
import org.scalactic.Requirements._

/**
 * Created by Alexander Shurmin.
 */
class ProductService(implicit productManager: ProductManager) extends BaseService {

  def get(id: String): Future[Option[Product]] = {
    logger.info(s"Get product by Id=$id")
    productManager.getById(id)
  }

  def get(offset: Int, limit: Int): Future[List[Product]] = {
    logger.info(s"Get products in range: offset=$offset, limit=$limit")
    productManager.getInRange(offset, limit)
  }

  def create(product: Product): Future[Product] = {
    require(product.id.isEmpty)
    logger.info(s"Creating product: $product")
    productManager.create(product)
  }

  def update(product: Product): Future[Product] = {
    logger.info(s"Updating product: $product")
    productManager.update(product)
  }

  def delete(id: String): Future[Boolean] = {
    logger.info(s"Deleting product by Id=$id")
    productManager.delete(id)
  }
}
