package org.challenge.eshop.ws.exception

/**
 * Created by Alexander Shurmin.
 */
case class ContentParseException(content: String) extends Exception(content)