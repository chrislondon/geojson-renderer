import mill._
import mill.api.Loose
import mill.define.Target
import mill.scalalib._
import mill.scalalib.publish._

object core extends SbtModule with PublishModule {
  def scalaVersion = "2.13.1"

  def publishVersion = "0.0.1"

  def pomSettings = PomSettings(
    description = "A command line tool to render geojson file on top of map tiles.",
    organization = "berlin.softwaretechnik",
    url = "https://github.com/softwaretechnik-berlin/geojson-renderer",
    licenses = Seq(License.MIT),
    versionControl = VersionControl.github("softwaretechnik-berlin", "geojson-renderer"),
    developers = Seq(
      Developer("softwaretechnik-berlin", "Softwaretechnik","https://github.com/softwaretechnik-berlin")
    )
  )

  override def ivyDeps: Target[Loose.Agg[Dep]] =
    Agg(
      ivy"org.scala-lang.modules::scala-xml::1.2.0",
      ivy"org.rogach::scallop:3.3.2",
      ivy"com.lihaoyi::upickle:0.9.5",
      ivy"org.apache.xmlgraphics:batik-transcoder:1.12",
      ivy"org.apache.xmlgraphics:batik-codec:1.12"
    )

  override def scalacOptions: Target[Seq[String]] =
    Seq(
      "-deprecation",
      "-feature",
      "-Xfatal-warnings",
    )

  object test extends Tests {
    def ivyDeps = Agg(
      ivy"org.eclipse.jgit:org.eclipse.jgit:5.7.0.202003110725-r",
      ivy"com.github.chocpanda::scalacheck-magnolia::0.3.1",
      ivy"org.scalatest::scalatest::3.1.1",
      ivy"org.scalatestplus::scalacheck-1-14::3.1.1.1"
    )
    def testFrameworks = Seq("org.scalatest.tools.Framework")
  }

  override def mainClass = Some("berlin.softwaretechnik.geojsonrenderer.Main")
}
