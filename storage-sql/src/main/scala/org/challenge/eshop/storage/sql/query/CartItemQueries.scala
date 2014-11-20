package org.challenge.eshop.storage.sql.query

import org.challenge.eshop.storage.sql.schema.EShopSchema
import org.challenge.eshop.storage.sql.schema.entity.CartItemEntity
import org.squeryl.PrimitiveTypeMode._

/**
 * Created by Alexander Shurmin.
 */
object CartItemQueries {

  def insert(items: List[CartItemEntity]): Unit = {
    inTransaction {
      EShopSchema.cartItem.insert(items)
    }
  }
}
