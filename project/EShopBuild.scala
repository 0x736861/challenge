import Dependencies._
import sbt.Keys._
import sbt._
import templemore.sbt.cucumber.CucumberPlugin


object EShopBuild extends Build {

  override def settings = super.settings ++ Seq(
    scalaVersion := "2.10.3",
    libraryDependencies ++= twitterUtil ++ Seq(scalactic, scalaTest, cucumberScala)
  )

  lazy val root = Project("eshop", file("."))
    .aggregate(core)

  lazy val common = Project("common", file("common"),
    settings = Seq(
      libraryDependencies ++= Seq(json4sJackson)
    )
  )

  lazy val core = Project("core", file("core"))
    .dependsOn(common, model, storageApi, security)
    .aggregate(common, model, storageApi, security)

  lazy val security = Project("security", file("security"),
    settings = Seq(
      libraryDependencies ++= Seq(shiro)
    ) ++ CucumberPlugin.cucumberSettingsWithTestPhaseIntegration
  )

  lazy val model = Project("model", file("model"))

  lazy val storageApi = Project("storage-api", file("storage-api"))
    .dependsOn(model)
    .aggregate(model)

  lazy val storageMemory = Project("storage-memory", file("storage-memory"))
    .dependsOn(common, storageApi)
    .aggregate(common, storageApi)

  lazy val ws = Project("ws", file("ws"),
    settings = Seq(
      libraryDependencies ++= Seq(finatra, typeSafeConfig)
    ))
    .settings(net.virtualvoid.sbt.graph.Plugin.graphSettings: _*)
    .dependsOn(core, storageMemory)
    .aggregate(core, storageMemory)

}