package org.challenge.eshop.storage.memory.manager

import com.twitter.util.Future
import org.challenge.eshop.model.IdEntity
import org.challenge.eshop.storage.api.manager.CRUDManager


/**
 * Created by Alexander Shurmin.
 */
trait BaseManager[T <: IdEntity[String]] extends CRUDManager[String, T] {

  var entities = Map.empty[String, T]


  override def getById(id: String): Future[Option[T]] = {
    Future.value(entities.get(id))
  }

  override def getInRange(offset: Int, limit: Int): Future[List[T]] = {
    Future.value(entities.toList.sortBy(_._1).drop(offset).take(limit).map(_._2))
  }

  override def create(model: T): Future[T] = {
    model.id match {
      case Some(id) =>
        entities = entities + (id -> model)
        Future.value(model)
      case None =>
        Future.exception(new IllegalArgumentException("id"))
    }
  }

  override def update(model: T): Future[Unit] = {
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
