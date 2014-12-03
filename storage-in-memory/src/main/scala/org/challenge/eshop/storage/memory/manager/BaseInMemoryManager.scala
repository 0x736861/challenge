package org.challenge.eshop.storage.memory.manager

import com.twitter.util.Future
import org.challenge.eshop.model.Entity
import org.challenge.eshop.storage.api.manager.BaseManager
import org.challenge.eshop.storage.memory.dao.BaseInMemoryDAO

/**
 * Created by Alexander Shurmin.
 */
trait BaseInMemoryManager[TValue <: Entity[String]] extends BaseManager[String, TValue] {

  val primaryDao: BaseInMemoryDAO[TValue]

  def getById(id: String): Future[Option[TValue]] = {
    primaryDao.getById(id)
  }

  def getInRange(offset: Int, limit: Int): Future[List[TValue]] = {
    primaryDao.getInRange(offset, limit)
  }

  def create(model: TValue): Future[TValue] = {
    primaryDao.create(model)
  }

  def update(model: TValue): Future[TValue] = {
    isExists(model) flatMap {
      case true =>
        primaryDao.update(model)
          .flatMap(_ => getById(model.id.get) map (_.get))
      case false => Future.exception(new Exception(s"Entity with Id=${model.id} not found"))
    }
  }

  def delete(id: String): Future[Boolean] = {
    primaryDao.delete(id) flatMap {
      case 0 => Future.False
      case 1 => Future.True
      case count => Future.exception(new Exception(s"Deleted $count entities"))
    }
  }

  private def isExists(model: TValue): Future[Boolean] = {
    model.id match {
      case Some(id) =>
        getById(id) map {
          case Some(_) => true
          case _ => false
        }
      case _ => Future.exception(new IllegalArgumentException("id"))
    }
  }
}