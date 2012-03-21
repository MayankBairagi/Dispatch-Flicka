package twine

import java.util.Properties
import java.io.InputStream
import java.io.OutputStream
import java.io.FileOutputStream
import java.io.FileInputStream

class PropertyReaderWriter(path: String) {

  def getValue(key: String): String = {

    var value = ""
    try {
      var in = new FileInputStream(path)
      properties.load(in)
      value = properties.getProperty(key)

      in.close()
      Thread.sleep(300)
    } catch {
      case e: Exception => {
        e.printStackTrace();
      }
    }
    if(value==null)
      ""
    else
      value.trim()
  }


  def setValue(key: String, value: String) {

    var out: OutputStream = null;

    try {

      var in = new FileInputStream(path)
      properties.load(in)
      out = new FileOutputStream(path)
      properties.setProperty(key, value.trim());
      properties.store(out, null)
      in.close()
      out.close()
      Thread.sleep(300)
    } catch {
      case e: Exception => {
        e.printStackTrace();
      }
    }

  }


  var properties: Properties = null
  properties = new Properties
}