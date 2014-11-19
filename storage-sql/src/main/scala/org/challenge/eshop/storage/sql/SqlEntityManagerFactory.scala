package org.challenge.eshop.storage.sql

import org.challenge.eshop.storage.api.EntityManagerFactory
import org.challenge.eshop.storage.api.manager.ProductManager
import org.challenge.eshop.storage.sql.manager.SqlProductManager

/**
 * Created by Alexander Shurmin.
 */
object SqlEntityManagerFactory extends EntityManagerFactory {

  override def productManager: ProductManager = SqlProductManager
}
