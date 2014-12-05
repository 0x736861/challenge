package org.challenge.eshop.storage.memory.dao.entity

import org.challenge.eshop.storage.api.dao.entity.BaseEntity

/**
 * Created by Alexander Shurmin.
 */
case class CustomerEntity(
  var id: Option[String],
  name: String) extends BaseEntity[String]