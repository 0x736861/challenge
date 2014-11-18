package org.challenge.eshop.common.converter


/**
 * Created by Alexander Shurmin.
 */
object TwitterFutureConverter {

  implicit class TwitterFutureToScalaFutureConverter[T](twitterFuture: com.twitter.util.Future[T]) {
    def toScala: scala.concurrent.Future[T] = {
      val scalaPromise = scala.concurrent.Promise[T]()
      twitterFuture
        .onSuccess(result => scalaPromise.success(result))
        .onFailure { t: Throwable => scalaPromise.failure(t)}
      scalaPromise.future
    }
  }
}