package org.challenge.eshop.storage.memory.manager

import com.twitter.util.Future
import org.challenge.eshop.model.Customer
import org.challenge.eshop.storage.api.manager.CustomerManager
import org.challenge.eshop.storage.memory.dao.entity.converter.CustomerEntityConverter._
import org.challenge.eshop.storage.memory.dao.CustomerInMemoryDAO
import org.challenge.eshop.storage.memory.dao.entity.CustomerEntity

/**
 * Created by Alexander Shurmin.
 */
class CustomerInMemoryManager extends BaseInMemoryManager[CustomerEntity] with CustomerManager {

  override val primaryDao = CustomerInMemoryDAO

  override def getById(id: String): Future[Option[Customer]] = {
    getEntityById(id).map(a => a.map(_.toModel))
  }

  override def getInRange(offset: Int, limit: Int): Future[List[Customer]] = {
    getEntitiesInRange(offset, limit).map(_.map(_.toModel))
  }

  override def create(model: Customer): Future[Customer] = {
    createEntity(model.toEntity).map(_.toModel)
  }

  override def update(model: Customer): Future[Customer] = {
    updateEntity(model.toEntity).map(_.toModel)
  }

  override def delete(id: String): Future[Boolean] = {
    deleteEntity(id)
  }
}
