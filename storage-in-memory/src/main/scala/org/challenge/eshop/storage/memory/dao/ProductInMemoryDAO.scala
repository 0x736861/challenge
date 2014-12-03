package org.challenge.eshop.storage.memory.dao

import org.challenge.eshop.model.Product
import org.challenge.eshop.storage.api.dao.ProductDAO

/**
 * Created by Alexander Shurmin.
 */
object ProductInMemoryDAO extends BaseInMemoryDAO[Product] with ProductDAO
