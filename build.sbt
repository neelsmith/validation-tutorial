name := "validation-tutorial"
organization := "io.github.hcmid"
version := "1.0.0"
licenses += ("GPL-3.0",url("https://opensource.org/licenses/gpl-3.0.html"))


libraryDependencies ++= Seq(
  "edu.holycross.shot.cite" %% "xcite" % "3.7.0",
  "edu.holycross.shot" %% "scm" % "6.2.0",
  "edu.holycross.shot" %% "ohco2" % "10.11.1",
  "edu.holycross.shot" %% "citeobj" % "7.2.0",
  "edu.holycross.shot" %% "cex" % "6.2.1",
  "edu.holycross.shot" %% "midvalidator" % "5.2.1",

  "edu.holycross.shot" %% "latphone" % "2.1.1",
  //"edu.holycross.shot.mid" %% "latin-text-model" % "1.3.0",
  "edu.holycross.shot" %% "virgapes" % "4.0.0"

)

tutTargetDirectory := file("docs")
tutSourceDirectory := file("tut")
enablePlugins(TutPlugin)
