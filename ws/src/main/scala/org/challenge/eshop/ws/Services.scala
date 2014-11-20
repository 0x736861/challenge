package org.challenge.eshop.ws

import org.challenge.eshop.core.service.{StoreService, CartService, ProductService}
import org.challenge.eshop.storage.api.EntityManagerFactory
import org.challenge.eshop.storage.sql.SqlEntityManagerFactory

/**
 * Created by Alexander Shurmin.
 */
object Services {

  implicit val entityManagerFactory: EntityManagerFactory = SqlEntityManagerFactory

  implicit val storeService = new StoreService

  implicit val productService = new ProductService

  implicit val cartService = new CartService

}
