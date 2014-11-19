package org.challenge.eshop.storage.api

import org.challenge.eshop.storage.api.manager.ProductManager

/**
 * Created by Alexander Shurmin.
 */
trait EntityManagerFactory {

  def productManager: ProductManager
}
