package org.challenge.eshop.storage.api.manager

import com.twitter.util.Future
import org.challenge.eshop.model.Cart

/**
 * Created by Alexander Shurmin.
 */
trait CartManager extends BaseManager[String, Cart] {

  def getCartByCustomerId(customerId: String): Future[Option[Cart]]
}
