package org.challenge.eshop.storage.sql.manager

import com.twitter.util.Future
import org.challenge.eshop.model.ProductInfo
import org.challenge.eshop.storage.api.ProductManager
import org.challenge.eshop.storage.sql.converter.ProductToEntityConverter._
import org.challenge.eshop.storage.sql.query.ProductQueries
import org.challenge.eshop.storage.sql.util.EntityIdGenerator

/**
 * Created by Alexander Shurmin.
 */
object SqlProductManager extends ProductManager {

  override def getById(id: String): Future[Option[ProductInfo]] = {
    Future.value(ProductQueries.getById(id))
  }

  override def create(entity: ProductInfo): Future[ProductInfo] = {
    val entityWithId = entity.copy(id = Some(EntityIdGenerator.nextId))
    Future.value(ProductQueries.create(entityWithId))
  }

  override def update(product: ProductInfo): Future[Unit] = {
    ProductQueries.update(product)
    Future.value(Unit)
  }

  override def delete(id: String): Future[Int] = {
    Future.value(ProductQueries.delete(id))
  }
}
