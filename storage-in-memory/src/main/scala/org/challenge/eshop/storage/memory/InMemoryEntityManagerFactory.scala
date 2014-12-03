package org.challenge.eshop.storage.memory

import org.challenge.eshop.storage.api.EntityManagerFactory
import org.challenge.eshop.storage.api.manager.ProductManager
import org.challenge.eshop.storage.memory.manager.ProductInMemoryManager

/**
 * Created by Alexander Shurmin.
 */
object InMemoryEntityManagerFactory extends EntityManagerFactory {

  lazy val productManager: ProductManager = {
    new ProductInMemoryManager
  }
}
