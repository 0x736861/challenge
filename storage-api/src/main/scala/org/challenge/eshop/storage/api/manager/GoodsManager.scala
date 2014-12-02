package org.challenge.eshop.storage.api.manager

import org.challenge.eshop.model.Goods
import org.challenge.eshop.storage.api.dao.{BaseDAO, GoodsDAO}

/**
 * Created by Alexander Shurmin.
 */
case class GoodsManager(implicit goodsDao: GoodsDAO) extends BaseManager[String, Goods] {

  override val primaryDao: BaseDAO[String, Goods] = goodsDao
}