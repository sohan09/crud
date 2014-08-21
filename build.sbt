name := """play2-crud-activator"""

version := "0.7.3-SNAPSHOT"

libraryDependencies ++= Seq(
  javaCore, javaJdbc, javaEbean,
  "postgresql" % "postgresql" % "9.1-901.jdbc4",
  "play2-crud" % "play2-crud_2.10" % "0.7.3-SNAPSHOT"
)

play.Project.playJavaSettings

resolvers += "release repository" at  "http://hakandilek.github.com/maven-repo/releases/"

resolvers += "snapshot repository" at "http://hakandilek.github.com/maven-repo/snapshots/"
