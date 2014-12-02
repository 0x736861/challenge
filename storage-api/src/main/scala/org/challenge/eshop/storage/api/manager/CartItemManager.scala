package org.challenge.eshop.storage.api.manager

import org.challenge.eshop.model.CartItem
import org.challenge.eshop.storage.api.dao.{BaseDAO, CartItemDAO}

/**
 * Created by Alexander Shurmin.
 */
case class CartItemManager(cartItemDao: CartItemDAO) extends BaseManager[String, CartItem] {

  override val primaryDao: BaseDAO[String, CartItem] = cartItemDao
}
