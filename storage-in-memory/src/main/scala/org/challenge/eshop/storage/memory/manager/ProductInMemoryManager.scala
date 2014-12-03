package org.challenge.eshop.storage.memory.manager

import org.challenge.eshop.model.Product
import org.challenge.eshop.storage.api.manager.ProductManager
import org.challenge.eshop.storage.memory.dao.ProductInMemoryDAO

/**
 * Created by Alexander Shurmin.
 */
class ProductInMemoryManager extends BaseInMemoryManager[Product] with ProductManager {

  override val primaryDao = ProductInMemoryDAO
}
