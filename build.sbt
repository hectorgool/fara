import scalariform.formatter.preferences._

name := "fara"

version := "3.0.1"

scalaVersion := "2.11.7"

resolvers := ("Atlassian Releases" at "https://maven.atlassian.com/public/") +: resolvers.value

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
  "com.adrianhurt"    %% "play-bootstrap3"         % "0.4.4-P24",
  "org.webjars"        % "angularjs"               % "1.4.8",
  "com.mohiva"        %% "play-silhouette"         % "3.0.2",
  "com.mohiva"        %% "play-silhouette-testkit" % "3.0.2" % "test",
  "com.typesafe.play" %% "play-slick"              % "1.0.1",
  "com.typesafe.play" %% "play-slick-evolutions"   % "1.0.1",
  "mysql"              % "mysql-connector-java"    % "5.1.37",
  "net.ceedubs"       %% "ficus"                   % "1.1.2",
  "net.codingwell"    %% "scala-guice"             % "4.0.0",
  "org.webjars"        % "bootstrap"               % "3.3.6",
  "org.webjars"       %% "webjars-play"            % "2.4.0-1",
  specs2 % Test,
  cache,
  evolutions,
  filters
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)

routesGenerator := InjectedRoutesGenerator

scalacOptions ++= Seq(
  "-deprecation", // Emit warning and location for usages of deprecated APIs.
  "-feature", // Emit warning and location for usages of features that should be imported explicitly.
  "-unchecked", // Enable additional warnings where generated code depends on assumptions.
  "-Xfatal-warnings", // Fail the compilation if there are any warnings.
  "-Xlint", // Enable recommended additional warnings.
  "-Ywarn-adapted-args", // Warn if an argument list is modified to match the receiver.
  "-Ywarn-dead-code", // Warn when dead code is identified.
  "-Ywarn-inaccessible", // Warn about inaccessible types in method signatures.
  "-Ywarn-nullary-override", // Warn when non-nullary overrides nullary, e.g. def foo() over def foo.
  "-Ywarn-numeric-widen" // Warn when numerics are widened.
)

//********************************************************
// Scalariform settings
//********************************************************

defaultScalariformSettings

ScalariformKeys.preferences := ScalariformKeys.preferences.value
  .setPreference(FormatXml, false)
  .setPreference(DoubleIndentClassDeclaration, false)
  .setPreference(PreserveDanglingCloseParenthesis, true)
