package org.challenge.eshop.common.converter

/**
 * Created by Alexander Shurmin.
 */
object ScalaToJavaTypeConverters {

  implicit def bigDecimalConverter(scalaDecimal: scala.math.BigDecimal): java.math.BigDecimal = {
    java.math.BigDecimal.valueOf(scalaDecimal.toDouble)
  }
}
