package org.challenge.eshop.core.service

import org.challenge.eshop.model.Goods
import org.challenge.eshop.storage.api.EntityManagerFactory

/**
 * Created by Alexander Shurmin.
 */
class ProductService(implicit entityManagerFactory: EntityManagerFactory) extends CRUDService[Goods] {

  override val entityManager = entityManagerFactory.productManager

}
