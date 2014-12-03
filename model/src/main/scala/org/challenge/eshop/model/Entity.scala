package org.challenge.eshop.model

/**
 * Created by Alexander Shurmin.
 */
trait Entity[TKey] {

  def id: Option[TKey]

}
