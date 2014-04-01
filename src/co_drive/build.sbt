name := "codrive"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache
)

resolvers ++= Seq(
  "Sonatype snapshots repository" at "https://oss.sonatype.org/content/repositories/snapshots/"
)

libraryDependencies ++= Seq(
  "org.webjars" %% "webjars-play" % "2.2.1-2",
  "org.webjars" % "bootstrap" % "3.1.1",
  "org.webjars" % "jquery" % "2.1.0-2",
  "org.webjars" % "font-awesome" % "4.0.3",
  "org.pac4j" % "play-pac4j_scala" % "1.2.0-SNAPSHOT",
  "org.pac4j" % "pac4j-oauth" % "1.5.0-SNAPSHOT"
)

play.Project.playJavaSettings
