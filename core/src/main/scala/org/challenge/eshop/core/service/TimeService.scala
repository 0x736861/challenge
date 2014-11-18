package org.challenge.eshop.core.service

import java.util.Calendar

/**
 * Created by Alexander Shurmin.
 */
object TimeService {

  def getTime = Calendar.getInstance().getTime.toString
}
