package org.challenge.eshop.storage.memory

import org.challenge.eshop.storage.api.EntityManagerFactory
import org.challenge.eshop.storage.api.manager.{CartItemManager, ProductManager}
import org.challenge.eshop.storage.memory.manager.{CartItemInMemoryManager, ProductInMemoryManager}

/**
 * Created by Alexander Shurmin.
 */
object InMemoryEntityManagerFactory extends EntityManagerFactory {

  lazy val cartItemManager: CartItemManager = {
    new CartItemInMemoryManager
  }

  lazy val productManager: ProductManager = {
    new ProductInMemoryManager
  }
}
