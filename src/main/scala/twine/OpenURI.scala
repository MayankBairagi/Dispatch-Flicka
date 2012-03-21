package twine

import java.awt._
import java.net.URI

/*object OpenURI {
  def main(argx: Array[String]): Unit = {
    var op = new OpenURI();
    op.openLink("http://www.astarinfotech.com");
  }
}     */

class OpenURI {
  def openLink(link: String) {
    var args: Array[String] = Array(link)
    if (!Desktop.isDesktopSupported) {
      System.err.println("Desktop is not supported (fatal)")
      System.exit(1)
    }
    if (args.length == 0) {
    }
    var desktop: Desktop = Desktop.getDesktop
    if (!desktop.isSupported(Desktop.Action.BROWSE)) {
      System.err.println("Desktop doesn't support the browse action (fatal)")
      System.exit(1)
    }
    for (arg <- args) {
      try {
        var uri: URI = new URI(arg)
        desktop.browse(uri)
      }
      catch {
        case e: Exception => {
          System.err.println(e.getMessage)
        }
      }
    }
  }
}