import Dependencies._
import sbt.Keys._
import sbt._


object EShopBuild extends Build {

  override def settings = super.settings ++ Seq(
    scalaVersion := "2.10.3",
    libraryDependencies ++= twitterUtil ++ Seq(scalactic, scalaTest)
  )

  lazy val root = Project("eshop", file("."))
    .aggregate(core)

  lazy val common = Project("common", file("common"),
    settings = Seq(
      libraryDependencies ++= Seq(json4sJackson)
    )
  )

  lazy val core = Project("core", file("core"))
    .dependsOn(common, model, storageApi)
    .aggregate(common, model, storageApi)

  lazy val model = Project("model", file("model"))

  lazy val storageApi = Project("storage-api", file("storage-api"))
    .dependsOn(model)
    .aggregate(model)

  lazy val storageInMemory = Project("storage-in-memory", file("storage-in-memory"))
    .dependsOn(common, storageApi)
    .aggregate(common, storageApi)

  lazy val ws = Project("ws", file("ws"),
    settings = Seq(
      libraryDependencies ++= Seq(finatra, typeSafeConfig, springSecurity)
    ))
    .settings(net.virtualvoid.sbt.graph.Plugin.graphSettings: _*)
    .dependsOn(core, storageInMemory)
    .aggregate(core, storageInMemory)

}