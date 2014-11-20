package org.challenge.eshop.storage.sql.schema.entity

import org.squeryl.KeyedEntity
import org.squeryl.annotations.Column

/**
 * Created by Alexander Shurmin.
 */
case class CartEntity(

  id: String

  ) extends KeyedEntity[String]
