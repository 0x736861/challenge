package org.challenge.eshop.storage.memory.dao

import org.challenge.eshop.model.Goods
import org.challenge.eshop.storage.api.dao.GoodsDAO

/**
 * Created by Alexander Shurmin.
 */
object GoodsInMemoryDAO extends BaseInMemoryDAO[Goods] with GoodsDAO
