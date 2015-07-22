name := "Leander-App"

organization := "org"

version := "0.0.1"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "net.databinder.dispatch" %% "dispatch-core" % "0.11.3",
  "org.apache.httpcomponents" % "httpclient" % "4.5",
  "org.jsoup" % "jsoup" % "1.8.2",
  "org.scalatest" %% "scalatest" % "2.2.5" % "test"
)