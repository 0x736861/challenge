import sbt._

object Dependencies {

  lazy val jacksonVersion = "2.2.2"
  lazy val twitterUtilVersion = "6.13.2"
  lazy val slf4jVersion = "1.7.7"

  lazy val twitterUtilCore = "com.twitter" % "util-core_2.10" % twitterUtilVersion
  lazy val twitterUtilLogging = "com.twitter" % "util-logging_2.10" % twitterUtilVersion
  lazy val twitterUtil = Seq(twitterUtilCore, twitterUtilLogging)

  lazy val jacksonDatabind = "com.fasterxml.jackson.core" % "jackson-databind" % jacksonVersion
  lazy val jacksonModuleScala = "com.fasterxml.jackson.module" % "jackson-module-scala_2.10" % jacksonVersion
  lazy val jackson = Seq(jacksonDatabind, jacksonModuleScala)

  lazy val squeryl = "org.squeryl" % "squeryl_2.10" % "0.9.5-6"
  lazy val c3p0 = "com.mchange" % "c3p0" % "0.9.5-pre8"
  lazy val h2 = "com.h2database" % "h2" % "1.4.181"

  lazy val slf4jApi = "org.slf4j" % "slf4j-api" % slf4jVersion
  lazy val slf4jSimple = "org.slf4j" % "slf4j-simple" % slf4jVersion
  lazy val slf4j = Seq(slf4jApi, slf4jSimple)

  lazy val finatra = "com.twitter" % "finatra_2.10" % "1.5.3"
  lazy val typeSafeConfig = "com.typesafe" % "config" % "1.2.1"

  lazy val scalaTest = "org.scalatest" % "scalatest_2.10" % "2.2.1" % Test
}