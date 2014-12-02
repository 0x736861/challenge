package org.challenge.eshop.storage.sql.manager

import com.twitter.util.Future
import org.challenge.eshop.common.EntityIdGenerator
import org.challenge.eshop.model.Goods
import org.challenge.eshop.storage.api.manager.GoodsManager
import org.challenge.eshop.storage.sql.converter.ProductEntityConverter._
import org.challenge.eshop.storage.sql.query.ProductQueries

/**
 * Created by Alexander Shurmin.
 */
object SqlProductManager extends GoodsManager {

  override def getById(id: String): Future[Option[Goods]] = {
    Future.value(ProductQueries.getById(id).map(_.toModel))
  }

  override def getInRange(offset: Int, limit: Int): Future[List[Goods]] = {
    Future.value(ProductQueries.getInRange(offset, limit).map(_.toModel))
  }

  override def create(product: Goods, id: Option[String] = None): Future[Goods] = {
    val preparedProduct = product.sku match {
      case Some(_) => product
      case _ => product.copy(sku = id.orElse(Some(EntityIdGenerator.nextId)))
    }
    Future.value(ProductQueries.create(preparedProduct.toEntity).toModel)
  }

  override def update(product: Goods): Future[Unit] = {
    ProductQueries.update(product.toEntity)
    Future.value(Unit)
  }

  override def delete(id: String): Future[Int] = {
    Future.value(ProductQueries.delete(id))
  }
}
