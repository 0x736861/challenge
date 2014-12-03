package org.challenge.eshop.storage.api.dao.entity

/**
 * Created by Alexander Shurmin.
 */
trait BaseEntity[TKey] {

  var id: Option[TKey]

}
