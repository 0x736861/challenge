package org.challenge.eshop.storage.api.manager

import com.twitter.util.Future
import org.challenge.eshop.model.ProductInfo

/**
 * Created by Alexander Shurmin.
 */
trait ProductManager {

  def getById(id: String): Future[Option[ProductInfo]]

  def getInRange(offset: Int, limit: Int): Future[List[ProductInfo]]

  def create(product: ProductInfo): Future[ProductInfo]

  def update(product: ProductInfo): Future[Unit]

  def delete(id: String): Future[Int]
}
