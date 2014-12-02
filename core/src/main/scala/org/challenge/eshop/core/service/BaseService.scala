package org.challenge.eshop.core.service

import com.twitter.logging.Logger

/**
 * Created by Alexander Shurmin.
 */
trait BaseService {

  protected lazy val logger = Logger(getClass)
}
