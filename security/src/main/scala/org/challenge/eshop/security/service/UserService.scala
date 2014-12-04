package org.challenge.eshop.security.service

import org.apache.shiro.SecurityUtils
import org.apache.shiro.subject.Subject

/**
 * Created by Alexander Shurmin.
 */
object UserService {

  def currentUser(): Subject = {
    SecurityUtils.getSubject
  }
}
