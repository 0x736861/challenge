package org.challenge.eshop.storage.api

import com.twitter.util.Future
import org.challenge.eshop.model.ProductInfo

/**
 * Created by Alexander Shurmin.
 */
trait ProductManager {

  def getById(id: String): Future[Option[ProductInfo]]

  def create(product: ProductInfo): Future[ProductInfo]

  def update(product: ProductInfo): Future[Unit]

  def delete(id: String): Future[Int]
}
