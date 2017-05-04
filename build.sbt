scalaVersion in ThisBuild := "2.12.2"

sbtVersion in ThisBuild := "0.13.15"

scalacOptions in ThisBuild := Seq("-unchecked", "-deprecation", "-feature")

lazy val root = (project in file(".")).
  settings(
    name := "proton",
    organization := "com.temerev",
    version := "0.0.1",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor" % "2.5.1",
      "com.typesafe.akka" %% "akka-stream" % "2.5.1",
      "com.typesafe.akka" %% "akka-http" % "10.0.6"
    )
  )

