package org.challenge.eshop.storage.memory.manager

import com.twitter.util.Future
import org.challenge.eshop.model.Product
import org.challenge.eshop.storage.api.manager.ProductManager
import org.challenge.eshop.storage.memory.dao.entity.converter.ProductEntityConverter._
import org.challenge.eshop.storage.memory.dao.ProductInMemoryDAO
import org.challenge.eshop.storage.memory.dao.entity.ProductEntity

/**
 * Created by Alexander Shurmin.
 */
class ProductInMemoryManager extends BaseInMemoryManager[ProductEntity] with ProductManager {

  override val primaryDao = ProductInMemoryDAO

  override def getById(id: String): Future[Option[Product]] = {
    getEntityById(id).map(a => a.map(_.toModel))
  }

  override def getInRange(offset: Int, limit: Int): Future[List[Product]] = {
    getEntitiesInRange(offset, limit).map(_.map(_.toModel))
  }

  override def create(model: Product): Future[Product] = {
    createEntity(model.toEntity).map(_.toModel)
  }

  override def update(model: Product): Future[Product] = {
    updateEntity(model.toEntity).map(_.toModel)
  }

  override def delete(id: String): Future[Boolean] = {
    deleteEntity(id)
  }
}
