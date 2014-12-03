package org.challenge.eshop.ws

import org.challenge.eshop.core.service.ProductService
import org.challenge.eshop.storage.api.EntityManagerFactory
import org.challenge.eshop.storage.memory.InMemoryEntityManagerFactory

/**
 * Created by Alexander Shurmin.
 */
object Services {

  val entityManagerFactory: EntityManagerFactory = InMemoryEntityManagerFactory

  implicit val productManager = entityManagerFactory.productManager

  implicit val productService = new ProductService
}
