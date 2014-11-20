package org.challenge.eshop.storage.sql.query

import org.challenge.eshop.storage.sql.schema.EShopSchema
import org.challenge.eshop.storage.sql.schema.entity.CartEntity

/**
 * Created by Alexander Shurmin.
 */
object CartQueries extends CRUDQueries {

  type EntityType = CartEntity

  val table = EShopSchema.cart
}
