name := """auth-playframework-poc"""
organization := "com.tudux"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.10"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
// Authentication related
// https://mvnrepository.com/artifact/com.pauldijou/jwt-play
libraryDependencies += "com.pauldijou" %% "jwt-play" % "5.0.0"

// https://mvnrepository.com/artifact/com.pauldijou/jwt-core
libraryDependencies += "com.pauldijou" %% "jwt-core" % "5.0.0"

// https://mvnrepository.com/artifact/com.auth0/jwks-rsa
libraryDependencies += "com.auth0" % "jwks-rsa" % "0.18.0"



// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.tudux.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.tudux.binders._"
