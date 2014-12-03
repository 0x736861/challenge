package org.challenge.eshop.model

/**
 * Created by Alexander Shurmin.
 */
case class Product(
  sku: Option[String] = None,
  name: String,
  price: Double) extends Entity[String] {

  override def id: Option[String] = sku

}
