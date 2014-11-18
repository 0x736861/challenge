package org.challenge.eshop.storage.sql.query

import org.challenge.eshop.storage.sql.schema.EShopSchema
import org.challenge.eshop.storage.sql.schema.entity.ProductEntity

/**
 * Created by Alexander Shurmin.
 */
object ProductQueries extends BaseQueries {

  type EntityType = ProductEntity

  val table = EShopSchema.product
}
