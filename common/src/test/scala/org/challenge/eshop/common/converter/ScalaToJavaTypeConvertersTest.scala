package org.challenge.eshop.common.converter

import org.scalatest.{Matchers, FunSuite}

/**
 * Created by Alexander Shurmin.
 */
class ScalaToJavaTypeConvertersTest extends FunSuite with Matchers {

  import ScalaToJavaTypeConverters._

  test("Scala BigDecimal should be converted to Java BigDecimal") {
    val scalaValue: scala.math.BigDecimal = 12.345
    val javaValue: java.math.BigDecimal = scalaValue
    scalaValue.toDouble shouldEqual javaValue.doubleValue
  }
}
