package org.challenge.eshop.core.service

import com.twitter.util.Future
import org.challenge.eshop.model.CartItem

/**
 * Created by Alexander Shurmin.
 */
class StoreService {

  def postponeToReserve(cartItems: List[CartItem]): Future[Boolean] = {
    Future.True
  }

  def restoreFromReserve(cartItems: List[CartItem]): Future[Unit] = {
    Future.Unit
  }
}
