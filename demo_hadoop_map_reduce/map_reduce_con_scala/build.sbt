version := "0.1.0-SNAPSHOT"

scalaVersion := "3.3.1"

val HadoopClientVersion = "3.3.6"
val ApacheMVNShadeVersion = "3.5.1"

lazy val root = (project in file("."))
  .settings(
    name := "HadoopMapReduce",

    idePackagePrefix := Some("org.kiramishima.labs"),
    libraryDependencies ++= Seq (
      "org.apache.hadoop" % "hadoop-client" % HadoopClientVersion,
      "org.apache.maven.plugins" % "maven-shade-plugin" % ApacheMVNShadeVersion
    )
  )
