name := "training"

version := "0.1"

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  "org.apache.hadoop"%"hadoop-common"%"2.8.4",
  "org.apache.hadoop"%"hadoop-hdfs"%"2.8.4",
  "org.apache.hadoop"%"hadoop-mapreduce-client-core"%"2.8.4",
  "org.apache.hadoop"%"hadoop-mapreduce-client-jobclient"%"2.8.4"
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