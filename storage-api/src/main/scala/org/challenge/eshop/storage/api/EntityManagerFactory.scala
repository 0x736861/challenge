package org.challenge.eshop.storage.api

import org.challenge.eshop.storage.api.manager.{CartManager, ProductManager}

/**
 * Created by Alexander Shurmin.
 */
trait EntityManagerFactory {

  def productManager: ProductManager

  def cartManager: CartManager
}
