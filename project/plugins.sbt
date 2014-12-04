logLevel := Level.Warn

resolvers += "Templemore Repository" at "http://templemore.co.uk/repo"

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.6.0")

addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.7.4")

addSbtPlugin("templemore" % "sbt-cucumber-plugin" % "0.8.0")