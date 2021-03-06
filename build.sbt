name := "slicker"

organization := "ro.igstan"

version := "0.1.0"

scalaVersion := "2.11.8"

javacOptions ++= Seq("-Xlint:unchecked", "-Xlint:deprecation")

scalacOptions ++= Seq(
  "-feature",
  "-unchecked",
  "-deprecation",
  "-language:implicitConversions",
  "-language:higherKinds",
  "-Xlint:_",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-inaccessible",
  "-Ywarn-infer-any",
  "-Ywarn-nullary-override",
  "-Ywarn-nullary-unit",
  "-Ywarn-numeric-widen",
  "-Ywarn-unused",
  "-Ywarn-value-discard"
)

libraryDependencies ++= Seq(
  "mysql"               % "mysql-connector-java" % "5.1.30",
  "com.typesafe.slick" %% "slick"                % "3.1.1",
  "org.scalatest"      %% "scalatest"            % "2.2.6" % "test"
)

testOptions in Test += Tests.Argument("-oF")
