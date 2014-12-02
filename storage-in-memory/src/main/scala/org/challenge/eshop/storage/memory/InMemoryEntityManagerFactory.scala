package org.challenge.eshop.storage.memory

import org.challenge.eshop.storage.api.EntityManagerFactory
import org.challenge.eshop.storage.api.manager.{CartItemManager, GoodsManager}
import org.challenge.eshop.storage.memory.dao.{CartItemInMemoryDAO, GoodsInMemoryDAO}
import org.challenge.eshop.storage.memory.manager.{CartItemInMemoryManager, GoodsInMemoryManager}

/**
 * Created by Alexander Shurmin.
 */
object InMemoryEntityManagerFactory extends EntityManagerFactory {

  lazy val cartItemManager: CartItemManager = {
    new CartItemInMemoryManager
  }

  lazy val goodsManager: GoodsManager = {
    new GoodsInMemoryManager
  }
}
