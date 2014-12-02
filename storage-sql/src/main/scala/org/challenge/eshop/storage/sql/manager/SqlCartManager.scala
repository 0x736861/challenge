package org.challenge.eshop.storage.sql.manager

import com.twitter.util.Future
import org.challenge.eshop.common.EntityIdGenerator
import org.challenge.eshop.model.{Cart, CartItem}
import org.challenge.eshop.storage.api.manager.CartManager
import org.challenge.eshop.storage.sql.converter.ProductEntityConverter._
import org.challenge.eshop.storage.sql.converter.{CartEntityConverter, CartItemEntityConverter}
import org.challenge.eshop.storage.sql.query.{CartItemQueries, CartQueries}

/**
 * Created by Alexander Shurmin.
 */
object SqlCartManager extends CartManager {

  override def getById(id: String): Future[Option[Cart]] = {
    Future.value(CartQueries.getById(id).map(CartEntityConverter.toModel))
  }

  override def getInRange(offset: Int, limit: Int): Future[List[Cart]] = ???

  def getCardWithItems(cartId: String): Future[Option[Cart]] = {
    CartQueries.getCartWithItems(cartId) match {
      case Nil => Future.None
      case cartWithItemEntities =>
        val cart = cartWithItemEntities.head._1
        val cartItems = cartWithItemEntities map {
          case (_, Some(productInfo), Some(quantity)) => CartItem(sku = productInfo.id, quantity = quantity, product = Some(productInfo.toModel))
        } match {
          case Nil => None
          case list => Some(list)
        }
        Future(Some(CartEntityConverter.toModel(cart).copy(items = cartItems)))
    }
  }

  override def create(cart: Cart, id: Option[String] = None): Future[Cart] = {
    val preparedCart = cart.id match {
      case Some(_) => cart
      case _ => cart.copy(id = id.orElse(Some(EntityIdGenerator.nextId)))
    }
    val entity = CartEntityConverter.toEntity(preparedCart)
    Future.value(CartQueries.create(entity)).map(CartEntityConverter.toModel)
  }

  override def update(cart: Cart): Future[Unit] = ???

  override def delete(id: String): Future[Int] = ???

  override def addItems(cartId: String, items: List[CartItem]): Future[Unit] = {
    CartItemQueries.insert(items.map(CartItemEntityConverter.toEntity(cartId, _)))
    Future.Unit
  }
}
