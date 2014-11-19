package org.challenge.eshop.common.converter

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper

/**
 * Created by Alexander Shurmin.
 */
object JsonConverter {

  private val mapper = {
    val objectMapper = new ObjectMapper with ScalaObjectMapper
    objectMapper.registerModule(DefaultScalaModule)
    objectMapper
  }

  def toJson[A <: AnyRef](a: A): String = {
    mapper.writeValueAsString(a)
  }

  def fromJson[A](json: String)(implicit mf: Manifest[A]): A = {
    mapper.readValue[A](json)
  }
}
