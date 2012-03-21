libraryDependencies ~= { seq =>
  val vers = "0.8.8"
  seq ++ Seq(
    "net.databinder" %% "dispatch-core" % vers,
    "net.databinder" %% "dispatch-oauth" % vers,
    "net.databinder" %% "dispatch-nio" % vers,
    /* Twine doesn't need the below dependencies, but it simplifies
     * the Dispatch tutorials to keep it here for now. */
    "net.databinder" %% "dispatch-http" % vers,
    "net.databinder" %% "dispatch-tagsoup" % vers,
    "net.databinder" %% "dispatch-jsoup" % vers,
	"net.databinder" %% "dispatch-json" % vers,
	"net.databinder" %% "dispatch-mime" % vers
  )
}

initialCommands := "import dispatch._"