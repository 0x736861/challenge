package org.challenge.eshop.common.converter

import org.json4s._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write}

/**
 * Created by Alexander Shurmin.
 */
object JsonConverter {

  implicit val formats = Serialization.formats(NoTypeHints)

  def toJson[A <: AnyRef](a: A): String = {
    write(a)
  }

  def fromJson[A](json: String)(implicit mf: Manifest[A]): A = {
    read(json)
  }
}
