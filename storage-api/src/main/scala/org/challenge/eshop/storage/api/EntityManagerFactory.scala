package org.challenge.eshop.storage.api

import org.challenge.eshop.storage.api.manager.{CartItemManager, ProductManager}

/**
 * Created by Alexander Shurmin.
 */
trait EntityManagerFactory {

  def cartItemManager: CartItemManager

  def productManager: ProductManager

}
