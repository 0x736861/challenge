package org.challenge.eshop.storage.sql.schema

import org.challenge.eshop.storage.sql.schema.entity.{CartEntity, CartItemEntity, ProductEntity}
import org.squeryl.PrimitiveTypeMode._
import org.squeryl.Schema

/**
 * Created by Alexander Shurmin.
 */
object EShopSchema extends Schema {

  // Tables

  val product = table[ProductEntity]("Product")

  val cart = table[CartEntity]("Cart")

  val cartItem = table[CartItemEntity]("CartItem")

  // Relations

  val cartToCartItemRelation = oneToManyRelation(cart, cartItem).via((c, ci) => c.id === ci.cardId)

  val productToCartItemRelation = oneToManyRelation(product, cartItem).via((p, ci) => p.id === ci.sku)

  // FK constraints

  cartToCartItemRelation.foreignKeyDeclaration.constrainReference(onDelete cascade, onUpdate cascade)

  productToCartItemRelation.foreignKeyDeclaration.constrainReference(onDelete cascade, onUpdate cascade)

  // Column attributes

  on(product)(p => declare(
    p.id is primaryKey
  ))

  on(cart)(c => declare(
    c.id is primaryKey
  ))
}
