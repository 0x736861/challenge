package org.challenge.eshop.storage.memory.manager

import org.challenge.eshop.model.CartItem
import org.challenge.eshop.storage.api.manager.CartItemManager
import org.challenge.eshop.storage.memory.dao.CartItemInMemoryDAO

/**
 * Created by Alexander Shurmin.
 */
class CartItemInMemoryManager extends BaseInMemoryManager[CartItem] with CartItemManager {

  override val primaryDao = CartItemInMemoryDAO
}
