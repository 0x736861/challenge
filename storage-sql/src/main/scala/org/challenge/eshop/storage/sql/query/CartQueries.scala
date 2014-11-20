package org.challenge.eshop.storage.sql.query

import org.challenge.eshop.storage.sql.schema.EShopSchema
import org.challenge.eshop.storage.sql.schema.EShopSchema._
import org.challenge.eshop.storage.sql.schema.entity.{CartEntity, ProductEntity}
import org.squeryl.PrimitiveTypeMode._

/**
 * Created by Alexander Shurmin.
 */
object CartQueries extends CRUDQueries {

  type EntityType = CartEntity

  val table = EShopSchema.cart

  def getCartWithItems(cartId: String): List[(CartEntity, Option[ProductEntity], Option[Double])] = {
    inTransaction {
      join(
        cart,
        cartItem.leftOuter,
        product.leftOuter
      )((c, c2ci, p) =>
        where(c.id === cartId)
          select(c, p, c2ci.map(_.quantity))
          on(c2ci.map(_.cardId) === Option(cartId), p.map(_.id) === c2ci.map(_.sku))
        ).toList
    }
  }
}
