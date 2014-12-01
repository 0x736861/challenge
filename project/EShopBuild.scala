import sbt._
import sbt.Keys._
import Dependencies._


object EShopBuild extends Build {

  override def settings = super.settings ++ Seq(
    scalaVersion := "2.10.3",
    libraryDependencies ++= twitterUtil ++ Seq(scalaTest)
  )

  lazy val root = Project("eshop", file("."))
    .aggregate(ws)

  lazy val common = Project("common", file("common"),
    settings = Seq(
      libraryDependencies ++= jackson
    )
  )

  lazy val core = Project("core", file("core"))
    .dependsOn(model, storageApi)

  lazy val model = Project("model", file("model"))

  lazy val storageApi = Project("storage-api", file("storage-api"))
    .dependsOn(model)

  lazy val storageSql = Project("storage-sql", file("storage-sql"),
    settings = Seq(
      libraryDependencies ++= slf4j ++ Seq(squeryl, c3p0, h2)
    ))
    .dependsOn(common, storageApi)

  lazy val ws = Project("ws", file("ws"),
    settings = Seq(
      libraryDependencies ++= Seq(finatra, typeSafeConfig)
    ))
    .dependsOn(core, storageSql)

}