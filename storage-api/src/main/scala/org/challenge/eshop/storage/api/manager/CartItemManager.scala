package org.challenge.eshop.storage.api.manager

import com.twitter.util.Future
import org.challenge.eshop.model.CartItem

/**
 * Created by Alexander Shurmin.
 */
trait CartItemManager {

  def getCartItem(cartId: String, sku: String): Future[Option[CartItem]]

  def update(cartItem: CartItem): Future[CartItem]

  def create(cartItem: CartItem): Future[CartItem]

  def delete(cartId: String, cartItemId: String): Future[Boolean]
}
