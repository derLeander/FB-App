name := "FBApp"

organization := "org.leander"

version := "0.0.1"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "net.databinder.dispatch" %% "dispatch-core" % "0.11.3",
  "org.apache.httpcomponents" % "httpclient" % "4.5",
  "org.jsoup" % "jsoup" % "1.8.2",
  "org.scalatest" %% "scalatest" % "2.2.5" % "test",
  "com.typesafe.slick" %% "slick" % "3.0.0",
  "com.github.nscala-time" %% "nscala-time" % "2.0.0",
  "org.sqldroid" % "sqldroid" % "1.0.3"
)

minSdkVersion in Android := "android-21"
