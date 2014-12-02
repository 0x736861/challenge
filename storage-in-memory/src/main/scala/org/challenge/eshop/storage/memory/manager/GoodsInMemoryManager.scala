package org.challenge.eshop.storage.memory.manager

import org.challenge.eshop.model.Goods
import org.challenge.eshop.storage.api.manager.GoodsManager
import org.challenge.eshop.storage.memory.dao.GoodsInMemoryDAO

/**
 * Created by Alexander Shurmin.
 */
class GoodsInMemoryManager extends BaseInMemoryManager[Goods] with GoodsManager {

  override val primaryDao = GoodsInMemoryDAO
}
