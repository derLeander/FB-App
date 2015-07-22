name := "Leander-App"

organization := "org"

version := "0.0.1"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
    "uk.co.bigbeeconsultants" %% "bee-client" % "0.28.0",
    "org.slf4j" % "slf4j-api" % "1.7.12",
    "ch.qos.logback" % "logback-core"    % "1.1.3",
    "ch.qos.logback" % "logback-classic" % "1.1.3"
)

resolvers += "Big Bee Consultants" at "http://repo.bigbeeconsultants.co.uk/repo"
