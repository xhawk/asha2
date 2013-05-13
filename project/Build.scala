import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "asha"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    "cglib" % "cglib" % "2.2.2",
    "org.activiti" % "activiti-engine" % "5.12.1",
    "org.springframework" % "spring-context" % "3.1.2.RELEASE",
    javaCore,
    javaJdbc,
    javaEbean
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here
    resolvers += "Alfresco Maven Repository" at "https://maven.alfresco.com/nexus/content/groups/public/"
  )

}
