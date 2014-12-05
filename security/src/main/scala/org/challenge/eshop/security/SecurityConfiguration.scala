package org.challenge.eshop.security

import org.apache.shiro.SecurityUtils
import org.apache.shiro.config.IniSecurityManagerFactory

/**
 * Created by Alexander Shurmin.
 */
object SecurityConfiguration {

  def init() {
    val factory = new IniSecurityManagerFactory("classpath:shiro.ini")
    val securityManager: org.apache.shiro.mgt.SecurityManager = factory.getInstance()
    SecurityUtils.setSecurityManager(securityManager)
  }
}
