package org.challenge.eshop.ws

import org.challenge.eshop.core.service.GoodsService
import org.challenge.eshop.storage.api.EntityManagerFactory
import org.challenge.eshop.storage.memory.manager.InMemoryEntityManagerFactory

/**
 * Created by Alexander Shurmin.
 */
object Services {

  val entityManagerFactory: EntityManagerFactory = InMemoryEntityManagerFactory

  implicit val goodsManager = entityManagerFactory.goodsManager

  implicit val goodsService = new GoodsService
}
