package org.challenge.eshop.storage.sql.schema

import org.challenge.eshop.storage.sql.schema.entity.ProductEntity
import org.squeryl.{Table, KeyedEntity, Schema}

import org.squeryl.PrimitiveTypeMode._

/**
 * Created by Alexander Shurmin.
 */
object EShopSchema extends Schema {

  // Tables

  val product = table[ProductEntity]("Product")


  // Column attributes

  on(product)(p => declare(
    p.id is primaryKey,
    p.description is dbType("TEXT")
  ))
}
