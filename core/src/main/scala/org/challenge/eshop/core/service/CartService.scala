package org.challenge.eshop.core.service

import com.twitter.util.Future
import org.challenge.eshop.model.{Cart, CartItem}
import org.challenge.eshop.storage.api.manager.{CartItemManager, CartManager}

/**
 * Created by Alexander Shurmin.
 */
class CartService(implicit
  cartManager: CartManager,
  cartItemManager: CartItemManager,
  customerService: CustomerService,
  productCountingService: ProductCountingService) extends BaseService {

  def getCart(customerId: String): Future[Cart] = {
    customerService.get(customerId) flatMap {
      case Some(customer) =>
        cartManager.getCartByCustomerId(customerId) flatMap {
          case Some(cart) => Future.value(cart)
          case _ => cartManager.create(Cart(customer = customer))
        }
      case _ => Future.exception(new Exception(s"Customer Id=$customerId not found"))
    }

  }

  def clearCart(cartId: String): Future[Cart] = {
    cartManager.getById(cartId) flatMap {
      case Some(cart) =>
        cart.items.foreach(cartItem => productCountingService.removeProductFromReserve(cartItem.sku, cartItem.quantity))
        val emptyCart = cart.copy(items = List.empty[CartItem])
        cartManager.update(emptyCart)
      case _ => Future.exception(new Exception(s"Cart Id=$cartId not found"))
    }
  }

  def addItemToCart(cartId: String, item: CartItem): Future[Cart] = {
    cartManager.exists(cartId) flatMap {
      case true =>
        productCountingService.deferProductToReserve(item.sku, item.quantity) flatMap {
          case true =>
            cartItemManager.getCartItem(cartId, item.sku) flatMap {
              // item with this sku was added early, updating quantity only
              case Some(existedItem) => cartItemManager.update(existedItem.copy(quantity = existedItem.quantity + item.quantity))

              // add new item
              case _ => cartItemManager.create(item)
            } onFailure (_ => {
              // if error happens - remove product from reserve
              productCountingService.removeProductFromReserve(item.sku, item.quantity)
            })

          case false =>
            Future.exception(new Exception(s"Can't defer product sku=${item.sku} with quantity=${item.quantity} to reserve"))
        }

      case false =>
        Future.exception(new Exception(s"Cart Id=$cartId not found"))
    } flatMap (_ => cartManager.getById(cartId).map(_.get))

  }

  def removeItemFromCart(cartId: String, itemId: String): Future[Cart] = {
    cartManager.exists(cartId) flatMap {
      case true => cartItemManager.getCartItem(cartId, itemId) flatMap {
        case Some(item) => productCountingService.removeProductFromReserve(item.sku, item.quantity) flatMap {
          case true => cartItemManager.delete(cartId, itemId)
            .onFailure(_ => productCountingService.deferProductToReserve(item.sku, item.quantity))
            .flatMap(_ => cartManager.getById(cartId).map(_.get))

          case false => Future.exception(new Exception(s"Can't remove product sku=${item.sku}, quantity=${item.quantity} from reserver"))
        }
        case _ => Future.exception(new Exception(s"CartItem Id=$itemId for cart=$cartId not found"))
      }
      case false => Future.exception(new Exception(s"Cart Id=$cartId not found"))
    }
  }

  def updateItemQuantity(cartId: String, itemId: String, quantity: Double): Future[Cart] = {
    cartManager.exists(cartId) flatMap {
      case true => cartItemManager
        .delete(cartId, itemId)
        .flatMap(_ => cartManager.getById(cartId).map(_.get))

      case false => Future.exception(new Exception(s"Cart Id=$cartId not found"))
    }
  }
}
