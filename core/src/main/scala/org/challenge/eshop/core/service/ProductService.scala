package org.challenge.eshop.core.service

import org.challenge.eshop.model.ProductInfo
import org.challenge.eshop.storage.api.EntityManagerFactory

/**
 * Created by Alexander Shurmin.
 */
class ProductService(implicit entityManagerFactory: EntityManagerFactory) extends BaseService[ProductInfo] {

  override val entityManager = entityManagerFactory.productManager

}
