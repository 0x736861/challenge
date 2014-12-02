package org.challenge.eshop.common

import scala.util.Random

/**
 * Created by Alexander Shurmin.
 */
object EntityIdGenerator {

  private val random = new Random(System.nanoTime())

  def nextId = random.alphanumeric.take(16).mkString("")
}
