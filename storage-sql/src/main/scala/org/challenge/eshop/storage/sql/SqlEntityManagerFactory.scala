package org.challenge.eshop.storage.sql

import org.challenge.eshop.storage.api.EntityManagerFactory
import org.challenge.eshop.storage.api.manager.{CartManager, GoodsManager}
import org.challenge.eshop.storage.sql.manager.{SqlCartManager, SqlProductManager}

/**
 * Created by Alexander Shurmin.
 */
object SqlEntityManagerFactory extends EntityManagerFactory {

  override def productManager: GoodsManager = SqlProductManager

  override def cartManager: CartManager = SqlCartManager
}
