package org.challenge.eshop.storage.sql.manager

import com.twitter.util.Future
import org.challenge.eshop.model.{CartItem, Cart}
import org.challenge.eshop.storage.api.manager.CartManager
import org.challenge.eshop.storage.sql.converter.CartEntityConverter._
import org.challenge.eshop.storage.sql.converter.CartItemEntityConverter
import org.challenge.eshop.storage.sql.query.{CartItemQueries, CartQueries}
import org.challenge.eshop.storage.sql.util.EntityIdGenerator

/**
 * Created by Alexander Shurmin.
 */
object SqlCartManager extends CartManager {

  override def getById(id: String): Future[Option[Cart]] = {
    Future.value(CartQueries.getById(id).map(_.toModel))
  }

  override def getInRange(offset: Int, limit: Int): Future[List[Cart]] = ???

  override def create(cart: Cart, id: Option[String] = None): Future[Cart] = {
    val preparedCart = cart.id match {
      case Some(_) => cart
      case _ => cart.copy(id = id.orElse(Some(EntityIdGenerator.nextId)))
    }
    Future.value(CartQueries.create(preparedCart.toEntity).toModel)
  }

  override def update(cart: Cart): Future[Unit] = ???

  override def delete(id: String): Future[Int] = ???

  override def addItems(cartId: String, items: List[CartItem]): Future[Unit] = {
    CartItemQueries.insert(items.map(CartItemEntityConverter.toEntity(cartId, _)))
    Future.Unit
  }
}
