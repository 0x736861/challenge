package com.challenge.eshop.security.service

import org.apache.shiro.authc.UsernamePasswordToken
import org.challenge.eshop.security.SecurityConfiguration
import org.challenge.eshop.security.service.UserService
import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers

/**
 * Created by Alexander Shurmin.
 */
class UserServiceTest extends FunSpec with ShouldMatchers {

  describe("UserService") {
    it("should get empty user") {
      SecurityConfiguration.init()

      val user = UserService.currentUser()
      user.isAuthenticated should equal(false)

      val token = new UsernamePasswordToken("lonestarr", "vespa")
      user.login(token)
      user.isAuthenticated should equal(true)

      println(s"User [${user.getPrincipal}] logged in successfully.")

      if (user.hasRole("schwartz")) {
        println("May the Schwartz be with you!")
      } else {
        println("Hello, mere mortal.")
      }

      user.logout()
    }
  }

}
