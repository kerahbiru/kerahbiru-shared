name := "kerahbiru-shared"

version := "0.1"

scalaVersion := "2.13.5"

enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin, UniversalPlugin)

// Optional: Include some nodejs types (useful for, say, accessing the env)
//libraryDependencies += "net.exoego" %%% "scala-js-nodejs-v12" % "0.9.1"

// Include scalatest
libraryDependencies += "org.scalatest" %%% "scalatest" % "3.2.2" % "test" // %%% (for scala.js lib) not %% (normal scala lib)

// Other dependencies

val circeVersion = "0.14.0-M4"
libraryDependencies += "io.circe" %%% "circe-core"    % circeVersion
libraryDependencies += "io.circe" %%% "circe-generic" % circeVersion
libraryDependencies += "io.circe" %%% "circe-parser"  % circeVersion

val enumeratumVersion = "1.6.1"
libraryDependencies += "com.beachape" %%% "enumeratum"       % enumeratumVersion
libraryDependencies += "com.beachape" %%% "enumeratum-circe" % enumeratumVersion

npmDependencies in Compile += "libphonenumber-js" -> "1.9.12"
