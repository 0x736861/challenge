package org.challenge.eshop.security

import org.apache.shiro.SecurityUtils
import org.apache.shiro.config.IniSecurityManagerFactory

/**
 * Created by Alexander Shurmin.
 */
object SecurityManager {

  def init() {
    val factory = new IniSecurityManagerFactory("classpath:shiro.ini")
    val securityManager = factory.getInstance()
    SecurityUtils.setSecurityManager(securityManager)
  }
}
