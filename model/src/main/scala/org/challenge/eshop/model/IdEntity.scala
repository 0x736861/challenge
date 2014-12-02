package org.challenge.eshop.model

/**
 * Created by Alexander Shurmin.
 */
trait IdEntity[TKey] {

  def id: Option[TKey]

}
