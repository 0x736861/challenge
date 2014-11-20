package org.challenge.eshop.core.service

import org.challenge.eshop.model.Cart
import org.challenge.eshop.storage.api.EntityManagerFactory

/**
 * Created by Alexander Shurmin.
 */
class CartService(implicit entityManagerFactory: EntityManagerFactory) extends BaseService[Cart] {

  override val entityManager = entityManagerFactory.cartManager

}