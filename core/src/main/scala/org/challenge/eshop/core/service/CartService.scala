package org.challenge.eshop.core.service

import com.twitter.logging.Logger
import com.twitter.util.Future
import org.challenge.eshop.model.{Cart, CartItem}
import org.challenge.eshop.storage.api.EntityManagerFactory

/**
 * Created by Alexander Shurmin.
 */
class CartService(implicit entityManagerFactory: EntityManagerFactory, storeService: StoreService) extends CRUDService[Cart] {

  val logger = Logger(getClass)

  override val entityManager = entityManagerFactory.cartManager

  def addToCart(cartId: String, items: List[CartItem]): Future[Unit] = {
    // getting rid of duplicate products
    val productsGroupedBySKU = items.groupBy(_.sku).map {
      case (sku, groupedItems) => CartItem(sku, groupedItems.foldLeft(0.0)(_ + _.quantity))
    }.toList

    storeService.postponeToReserve(productsGroupedBySKU) map {
      case true => entityManager.addItems(cartId, productsGroupedBySKU).rescue {
        case e: Exception =>
          logger.error(e, "Can't add products to cart, restore products from reserve")
          storeService.restoreFromReserve(productsGroupedBySKU)
      }

      case false => logger.warning(s"Can't postpone all products to reserve")
    }
  }
}