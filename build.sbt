name := """xstore"""

version := "1.0.0"

libraryDependencies ++= Seq(
  javaCore, javaJdbc, javaEbean,
  "postgresql" % "postgresql" % "9.1-901.jdbc4",
  "mysql" % "mysql-connector-java" % "5.1.32",
  "play2-crud" % "play2-crud_2.10" % "0.7.3-SNAPSHOT"
)

play.Project.playJavaSettings

resolvers += "release repository" at  "http://hakandilek.github.com/maven-repo/releases/"

resolvers += "snapshot repository" at "http://hakandilek.github.com/maven-repo/snapshots/"
