package org.challenge.eshop.core.service

import com.twitter.util.Future
import org.challenge.eshop.model.ProductInfo
import org.challenge.eshop.storage.api.EntityManagerFactory
import org.challenge.eshop.storage.api.manager.ProductManager

/**
 * Created by Alexander Shurmin.
 */
class ProductService(implicit entityManagerFactory: EntityManagerFactory) {

  private lazy val productManager = entityManagerFactory.productManager

  def getById(id: String): Future[Option[ProductInfo]] = {
    productManager.getById(id)
  }

  def getInRange(offset: Int, limit: Int): Future[List[ProductInfo]] = {
    productManager.getInRange(offset, limit)
  }

  def create(product: ProductInfo): Future[ProductInfo] = {
    productManager.create(product)
  }

  def update(product: ProductInfo): Future[Unit] = {
    productManager.update(product)
  }

  def delete(id: String): Future[Boolean] = {
    productManager.delete(id).map(_ == 1)
  }
}
