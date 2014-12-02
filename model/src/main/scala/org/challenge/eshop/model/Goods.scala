package org.challenge.eshop.model

/**
 * Created by Alexander Shurmin.
 */
case class Goods(
  sku: Option[String] = None,
  name: String,
  price: Double) extends IdEntity[String] {

  override def id: Option[String] = sku

}
