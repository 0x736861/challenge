package org.challenge.eshop.storage.memory.manager

import org.challenge.eshop.storage.api.EntityManagerFactory
import org.challenge.eshop.storage.api.manager.{CartItemManager, GoodsManager}
import org.challenge.eshop.storage.memory.dao.{CartItemInMemoryDAO, GoodsInMemoryDAO}

/**
 * Created by Alexander Shurmin.
 */
object InMemoryEntityManagerFactory extends EntityManagerFactory {

  lazy val cartItemManager: CartItemManager = {
    new CartItemManager(CartItemInMemoryDAO)
  }

  lazy val goodsManager: GoodsManager = {
    new GoodsManager(GoodsInMemoryDAO)
  }
}
