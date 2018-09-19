import Dependencies._

lazy val root = (project in file(".")).settings(
  inThisBuild(
    List(
      organization := "com.rainist",
      scalaVersion := "2.12.6"
    )
  ),
  name := "Grammar",
  version := "0.0.1",
  scalacOptions ++= Seq(
    "-unchecked",
    "-feature",
    "-deprecation",
    "-language:postfixOps",
    "-language:implicitConversions"
  ),
  libraryDependencies ++= Seq(
    testDependencies
  ).flatten,
  scalafmtOnCompile := true,
  autoCompilerPlugins := true,
  assemblyJarName in assembly := "grammar.jar"
)
