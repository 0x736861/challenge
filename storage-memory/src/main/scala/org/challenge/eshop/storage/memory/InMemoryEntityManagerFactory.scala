package org.challenge.eshop.storage.memory

import org.challenge.eshop.storage.api.EntityManagerFactory
import org.challenge.eshop.storage.api.manager.{CustomerManager, ProductManager}
import org.challenge.eshop.storage.memory.manager.{CustomerInMemoryManager, ProductInMemoryManager}

/**
 * Created by Alexander Shurmin.
 */
object InMemoryEntityManagerFactory extends EntityManagerFactory {

  lazy val productManager: ProductManager = new ProductInMemoryManager

  lazy val customerManager: CustomerManager = new CustomerInMemoryManager

}
