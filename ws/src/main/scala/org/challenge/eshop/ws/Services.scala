package org.challenge.eshop.ws

import org.challenge.eshop.core.service.ProductService
import org.challenge.eshop.storage.api.EntityManagerFactory
import org.challenge.eshop.storage.sql.SqlEntityManagerFactory

/**
 * Created by Alexander Shurmin.
 */
object Services {

  implicit val entityManagerFactory: EntityManagerFactory = SqlEntityManagerFactory

  implicit val productService = new ProductService

}
