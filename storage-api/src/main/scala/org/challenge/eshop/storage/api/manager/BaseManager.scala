package org.challenge.eshop.storage.api.manager

import com.twitter.util.Future
import org.challenge.eshop.model.IdEntity
import org.challenge.eshop.storage.api.dao.BaseDAO

/**
 * Created by Alexander Shurmin.
 */
trait BaseManager[TKey, TValue <: IdEntity[TKey]] {

  val primaryDao: BaseDAO[TKey, TValue]

  def getById(id: TKey): Future[Option[TValue]] = {
    primaryDao.getById(id)
  }

  def getInRange(offset: Int, limit: Int): Future[List[TValue]] = {
    primaryDao.getInRange(offset, limit)
  }

  def create(model: TValue): Future[TValue] = {
    primaryDao.create(model)
  }

  def update(model: TValue): Future[Unit] = {
    primaryDao.update(model)
  }

  def delete(id: String): Future[Int] = {
    primaryDao.delete(id)
  }
}
