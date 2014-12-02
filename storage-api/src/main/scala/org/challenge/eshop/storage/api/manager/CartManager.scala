package org.challenge.eshop.storage.api.manager

import com.twitter.util.Future
import org.challenge.eshop.model.{CartItem, Cart}

/**
 * Created by Alexander Shurmin.
 */
trait CartManager extends CRUDManager[String, Cart] {

  def addItems(cartId: String, items: List[CartItem]): Future[Unit]

  def getCardWithItems(cartId: String): Future[Option[Cart]]

}
