import sbt._

object Dependencies {
  private lazy val scalaTestVersion = "3.0.5"

  lazy val testDependencies: Seq[ModuleID] = Seq(
    "org.scalatest" %% "scalatest"
  ).map(_ % scalaTestVersion)
}
