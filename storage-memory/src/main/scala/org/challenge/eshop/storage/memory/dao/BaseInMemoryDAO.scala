package org.challenge.eshop.storage.memory.dao

import com.twitter.util.Future
import org.challenge.eshop.common.EntityIdGenerator
import org.challenge.eshop.storage.api.dao.BaseDAO
import org.challenge.eshop.storage.api.dao.entity.BaseEntity


/**
 * Created by Alexander Shurmin.
 */
trait BaseInMemoryDAO[TEntity <: BaseEntity[String]] extends BaseDAO[String, TEntity] {

  var entities = Map.empty[String, TEntity]

  override def getById(id: String): Future[Option[TEntity]] = {
    Future.value(entities.get(id))
  }

  override def getInRange(offset: Int, limit: Int): Future[List[TEntity]] = {
    Future.value(entities.toList.sortBy(_._1).drop(offset).take(limit).map(_._2))
  }

  override def create(model: TEntity): Future[TEntity] = {
    val modelWithId = model.id match {
      case Some(_) => model
      case _ =>
        model.id = Some(EntityIdGenerator.nextId)
        model
    }
    entities = entities + (modelWithId.id.get -> modelWithId)
    Future.value(model)
  }

  override def update(model: TEntity): Future[Unit] = {
    model.id match {
      case Some(id) =>
        entities = entities + (id -> model)
        Future.Unit
      case _ => Future.exception(new IllegalArgumentException("id"))
    }
  }

  override def delete(id: String): Future[Int] = {
    entities.contains(id) match {
      case true =>
        entities = entities - id
        Future.value(1)
      case false =>
        Future.value(0)
    }
  }
}
