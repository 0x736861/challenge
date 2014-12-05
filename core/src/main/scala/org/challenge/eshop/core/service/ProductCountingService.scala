package org.challenge.eshop.core.service

import com.twitter.util.Future
import org.challenge.eshop.model.Customer

/**
 * Created by Alexander Shurmin.
 */
class ProductCountingService {

  def deferProductToReserve(sku: String, quantity: Double): Future[Boolean] = {
    Future.value(true)
  }

  def removeProductFromReserve(sku: String, quantity: Double): Future[Boolean] = {
    Future.value(true)
  }

  def shipProduct(sku: String, quantity: Double): Future[Boolean] = {
    Future.value(true)
  }

}
