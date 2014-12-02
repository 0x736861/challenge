package org.challenge.eshop.storage.api

import org.challenge.eshop.storage.api.manager.{CartItemManager, GoodsManager}

/**
 * Created by Alexander Shurmin.
 */
trait EntityManagerFactory {

  def cartItemManager: CartItemManager

  def goodsManager: GoodsManager

}
