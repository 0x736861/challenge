package org.challenge.eshop.storage.memory.dao

import com.twitter.util.Future
import org.challenge.eshop.model.IdEntity
import org.challenge.eshop.storage.api.dao.BaseDAO


/**
 * Created by Alexander Shurmin.
 */
trait BaseInMemoryDAO[TValue <: IdEntity[String]] extends BaseDAO[String, TValue] {

  var entities = Map.empty[String, TValue]

  override def getById(id: String): Future[Option[TValue]] = {
    Future.value(entities.get(id))
  }

  override def getInRange(offset: Int, limit: Int): Future[List[TValue]] = {
    Future.value(entities.toList.sortBy(_._1).drop(offset).take(limit).map(_._2))
  }

  override def create(model: TValue): Future[TValue] = {
    model.id match {
      case Some(id) =>
        entities = entities + (id -> model)
        Future.value(model)
      case None =>
        Future.exception(new IllegalArgumentException("id"))
    }
  }

  override def update(model: TValue): Future[Unit] = {
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
