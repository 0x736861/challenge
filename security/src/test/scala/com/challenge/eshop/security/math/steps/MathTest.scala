package com.challenge.eshop.security.math.steps

import cucumber.api.scala.{EN, ScalaDsl}
import org.scalatest.matchers.ClassicMatchers

/**
 * Created by Alexander Shurmin.
 */

class MathTest extends ScalaDsl with EN with ClassicMatchers {

  var x: Int = 0

  Given("^a variable x with value (\\d+)$") { value: Int =>
    x = value
  }

  When("^I multiply x by (\\d+)$") { value: Int =>
    x = x * value
  }

  Then("^x should equal (\\d+)$") { value: Int =>
    if (value != x)
      throw new RuntimeException("x is " + x + ", but should be " + value);
  }

}