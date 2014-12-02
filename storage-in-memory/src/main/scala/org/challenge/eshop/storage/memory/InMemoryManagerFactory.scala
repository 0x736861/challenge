package org.challenge.eshop.storage.memory

import org.challenge.eshop.storage.api.EntityManagerFactory
import org.challenge.eshop.storage.api.manager.{ProductManager, CartManager}

/**
 * Created by Alexander Shurmin.
 */
object InMemoryManagerFactory extends EntityManagerFactory{
  override def productManager: ProductManager = ???

  override def cartManager: CartManager = ???
}
