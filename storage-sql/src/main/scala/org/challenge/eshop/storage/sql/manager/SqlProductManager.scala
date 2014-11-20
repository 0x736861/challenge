package org.challenge.eshop.storage.sql.manager

import com.twitter.util.Future
import org.challenge.eshop.model.ProductInfo
import org.challenge.eshop.storage.api.manager.ProductManager
import org.challenge.eshop.storage.sql.converter.ProductEntityConverter._
import org.challenge.eshop.storage.sql.query.ProductQueries
import org.challenge.eshop.storage.sql.util.EntityIdGenerator

/**
 * Created by Alexander Shurmin.
 */
object SqlProductManager extends ProductManager {

  override def getById(id: String): Future[Option[ProductInfo]] = {
    Future.value(ProductQueries.getById(id).map(_.toModel))
  }

  override def getInRange(offset: Int, limit: Int): Future[List[ProductInfo]] = {
    Future.value(ProductQueries.getInRange(offset, limit).map(_.toModel))
  }

  override def create(product: ProductInfo, id: Option[String] = None): Future[ProductInfo] = {
    val preparedProduct = product.id match {
      case Some(_) => product
      case _ => product.copy(id = id.orElse(Some(EntityIdGenerator.nextId)))
    }
    Future.value(ProductQueries.create(preparedProduct.toEntity).toModel)
  }

  override def update(product: ProductInfo): Future[Unit] = {
    ProductQueries.update(product.toEntity)
    Future.value(Unit)
  }

  override def delete(id: String): Future[Int] = {
    Future.value(ProductQueries.delete(id))
  }
}
