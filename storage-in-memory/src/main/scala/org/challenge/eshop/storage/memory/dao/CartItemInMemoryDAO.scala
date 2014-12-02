package org.challenge.eshop.storage.memory.dao

import org.challenge.eshop.model.CartItem
import org.challenge.eshop.storage.api.dao.CartItemDAO

/**
 * Created by Alexander Shurmin.
 */
object CartItemInMemoryDAO extends BaseInMemoryDAO[CartItem] with CartItemDAO
