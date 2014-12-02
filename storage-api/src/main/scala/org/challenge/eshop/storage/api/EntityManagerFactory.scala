package org.challenge.eshop.storage.api

import org.challenge.eshop.storage.api.manager.GoodsManager

/**
 * Created by Alexander Shurmin.
 */
trait EntityManagerFactory {

  def goodsManager: GoodsManager

}
