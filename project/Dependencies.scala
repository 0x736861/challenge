import sbt._

object Dependencies {

  val jacksonVersion = "2.2.2"
  val twitterUtilVersion = "6.13.2"
  val slf4jVersion = "1.7.7"

  val twitterUtilCore = "com.twitter" % "util-core_2.10" % twitterUtilVersion
  val twitterUtilLogging = "com.twitter" % "util-logging_2.10" % twitterUtilVersion
  val twitterUtil = Seq(twitterUtilCore, twitterUtilLogging)

  val json4sJackson = "org.json4s" %% "json4s-jackson" % "3.2.11"

  val scalactic = "org.scalactic" % "scalactic_2.10" % "2.2.1"

  val squeryl = "org.squeryl" % "squeryl_2.10" % "0.9.5-6"
  val c3p0 = "com.mchange" % "c3p0" % "0.9.5-pre8"
  val h2 = "com.h2database" % "h2" % "1.4.181"

  val slf4jApi = "org.slf4j" % "slf4j-api" % slf4jVersion
  val slf4jSimple = "org.slf4j" % "slf4j-simple" % slf4jVersion
  val slf4j = Seq(slf4jApi, slf4jSimple)

  val finatra = "com.twitter" % "finatra_2.10" % "1.5.3"

  val typeSafeConfig = "com.typesafe" % "config" % "1.2.1"

  val scalaTest = "org.scalatest" % "scalatest_2.10" % "2.2.1" % Test
}