package org.challenge.eshop.core.service

import com.twitter.util.Future
import org.challenge.eshop.model.Goods
import org.challenge.eshop.storage.api.manager.GoodsManager

/**
 * Created by Alexander Shurmin.
 */
class GoodsService(implicit goodsManager: GoodsManager) extends BaseService {

  def get(id: String): Future[Option[Goods]] = {
    logger.info(s"Get goods by Id=$id")
    goodsManager.getById(id)
  }

  def get(offset: Int, limit: Int): Future[List[Goods]] = {
    logger.info(s"Get goods in range: offset=$offset, limit=$limit")
    goodsManager.getInRange(offset, limit)
  }

  def create(goods: Goods): Future[Goods] = {
    logger.info(s"Creating goods: $goods")
    goodsManager.create(goods)
  }

  def update(goods: Goods): Future[Goods] = {
    logger.info(s"Updating goods: $goods")
    goodsManager.update(goods)
  }

  def delete(id: String): Future[Boolean] = {
    logger.info(s"Deleting goods by Id=$id")
    goodsManager.delete(id)
  }
}
