package org.challenge.eshop.storage.api.manager

import com.twitter.util.Future
import org.challenge.eshop.model.ProductInfo

/**
 * Created by Alexander Shurmin.
 */
trait CRUDManager[T] {

  def getById(id: String): Future[Option[T]]

  def getInRange(offset: Int, limit: Int): Future[List[T]]

  def create(model: T, id: Option[String] = None): Future[T]

  def update(model: T): Future[Unit]

  def delete(id: String): Future[Int]
}
