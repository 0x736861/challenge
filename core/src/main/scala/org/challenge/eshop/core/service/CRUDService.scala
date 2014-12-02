package org.challenge.eshop.core.service

import com.twitter.util.Future
import org.challenge.eshop.storage.api.manager.BaseManager

/**
 * Created by Alexander Shurmin.
 */
trait CRUDService[T] {

  val entityManager: BaseManager[T]

  def getById(id: String): Future[Option[T]] = {
    entityManager.getById(id)
  }

  def getInRange(offset: Int, limit: Int): Future[List[T]] = {
    entityManager.getInRange(offset, limit)
  }

  def create(model: T, id: Option[String] = None): Future[T] = {
    entityManager.create(model, id)
  }

  def update(model: T): Future[Unit] = {
    entityManager.update(model)
  }

  def delete(id: String): Future[Boolean] = {
    entityManager.delete(id).map(_ == 1)
  }
}
