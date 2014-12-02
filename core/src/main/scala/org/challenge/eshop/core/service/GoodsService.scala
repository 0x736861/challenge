package org.challenge.eshop.core.service

import com.twitter.util.Future
import org.challenge.eshop.model.Goods
import org.challenge.eshop.storage.api.manager.GoodsManager

/**
 * Created by Alexander Shurmin.
 */
class GoodsService(implicit goodsManager: GoodsManager) extends BaseService {

  def get(id: String): Future[Option[Goods]] = {
    logger.info(s"Get by Id: " + id)
    goodsManager.getById(id)
  }

  def get(offset: Int, limit: Int): Future[List[Goods]] = {
    logger.info(s"Get in range: offset=$offset, limit=$limit")
    goodsManager.getInRange(offset, limit)
  }

  def create(goods: Goods): Future[Goods] = {
    logger.info("Creating goods: " + goods)
    goodsManager.create(goods)
  }
}
