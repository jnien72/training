name := "training"

version := "0.1"

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  "org.http4s"%% "http4s-blaze-server" % "0.15.4a",
  "org.http4s"%% "http4s-dsl" % "0.15.4a",
  "org.http4s"%% "http4s-argonaut" % "0.15.4a"
)

updateOptions := updateOptions.value
  .withCachedResolution(true)

libraryDependencies ~= { _
  .map(_.exclude("javax.jms","jms"))
  .map(_.exclude("com.sun.jdmk","jmxtools"))
  .map(_.exclude("com.sun.jmx","jmxri"))
}


assemblyMergeStrategy in assembly :={
  case x if x.startsWith("META-INF")
    && x.endsWith("MANIFEST.MF") => MergeStrategy.discard
  case x => MergeStrategy.first
}