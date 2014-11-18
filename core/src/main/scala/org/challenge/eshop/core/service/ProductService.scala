package org.challenge.eshop.core.service

import com.twitter.util.Future
import org.challenge.eshop.model.ProductInfo

/**
 * Created by Alexander Shurmin.
 */
class ProductService {

  def create(product: ProductInfo): Future[ProductInfo] = {
    ???
  }

  def update(product: ProductInfo): Future[Unit] = {
    ???
  }

  def getById(id: String): Future[Option[ProductInfo]] = {
    ???
  }

  def delete(id: String): Future[Boolean] = {
    ???
  }

}
