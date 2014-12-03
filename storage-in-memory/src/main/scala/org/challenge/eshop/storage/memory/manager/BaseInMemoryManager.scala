package org.challenge.eshop.storage.memory.manager

import com.twitter.util.Future
import org.challenge.eshop.common.EntityIdGenerator
import org.challenge.eshop.storage.api.dao.entity.BaseEntity
import org.challenge.eshop.storage.memory.dao.BaseInMemoryDAO

/**
 * Created by Alexander Shurmin.
 */
trait BaseInMemoryManager[TEntity <: BaseEntity[String]] {

  val primaryDao: BaseInMemoryDAO[TEntity]

  def getEntityById(id: String): Future[Option[TEntity]] = {
    primaryDao.getById(id)
  }

  def getEntitiesInRange(offset: Int, limit: Int): Future[List[TEntity]] = {
    primaryDao.getInRange(offset, limit)
  }

  def createEntity(model: TEntity): Future[TEntity] = {
    model.id = Some(EntityIdGenerator.nextId)
    primaryDao.create(model)
  }

  def updateEntity(model: TEntity): Future[TEntity] = {
    isExists(model) flatMap {
      case true =>
        primaryDao.update(model)
          .flatMap(_ => getEntityById(model.id.get) map (_.get))
      case false => Future.exception(new Exception(s"Entity with Id=${model.id} not found"))
    }
  }

  def deleteEntity(id: String): Future[Boolean] = {
    primaryDao.delete(id) flatMap {
      case 0 => Future.False
      case 1 => Future.True
      case count => Future.exception(new Exception(s"Deleted $count entities"))
    }
  }

  private def isExists(model: TEntity): Future[Boolean] = {
    model.id match {
      case Some(id) =>
        getEntityById(id) map {
          case Some(_) => true
          case _ => false
        }
      case _ => Future.exception(new IllegalArgumentException("id"))
    }
  }
}