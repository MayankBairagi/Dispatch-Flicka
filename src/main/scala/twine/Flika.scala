
package twine
import twine._

import javax.crypto
import org.apache.http.protocol.HTTP._

import java.net.URLDecoder
import dispatch.url._
import dispatch.{url, Http, Request}

/**
 * Created by IntelliJ IDEA.
 * User: Astar
 * Date: 2/24/12
 * Time: 5:54 PM
 * To change this template use File | Settings | File Templates.
 */

object Flika {

  private def bytes(str: String) = str.getBytes(UTF_8)

/*  def main(arg:Array[String])
  {
    System.out.println("Welcome To Flika: A Command lind Fliker Utility")
    val consumer = Consumer("fa1984bd2ecc877d76064baf7e6cc53d", "a419b0636b7054aa&")
    val method="GET"
    val baseURL=  "http://www.flickr.com/services/oauth/request_token"
    import scala.collection.mutable.HashMap
    val mapx = new HashMap[String, Any]

     val signature= OAuth.sign(method,baseURL,mapx,consumer, None,Some(""),Some("oob"));

   // System.out.println("xxxx "+signature)
       var callable=baseURL+"/?";
    signature foreach (x => if(!x._1.equals("oauth_signature")) { callable+=x._1+"="+x._2+"&"})
    signature foreach (x => if(x._1.equals("oauth_signature")) { callable+=x._1+"="+URLEncoder.encode(x._2,"UTF-8")})
    System.out.println(callable)

    val h=new Http

    val req =url(callable)
    val handler=req >>> System.out
    var responce=h(handler)
    System.out.println("Responce  "+responce)

   System.out.println("Decoded URL = "+ URLDecoder.decode(callable,"UTF-8"))
   // System.out.println("OAuth Key = "+ signature("oauth_consumer_key"))
    //  signature.toList sortBy ( _._1 ) foreach {
   //   case (key, value) =>
   //     println(key + " = " + value)
   // }


    val message = "GET&http%3A%2F%2Fwww.flickr.com%2Fservices%2Foauth%2Frequest_token%2F&oauth_callback%3Doob%26oauth_consumer_key%3Dfa1984bd2ecc877d76064baf7e6cc53d%26oauth_nonce%3D14089723295269%26oauth_signature_method%3DHMAC-SHA1%26oauth_timestamp%3D1330719040%26oauth_verifier%3D%26oauth_version%3D1.0"


    val SHA1 = "HmacSHA1"
   // val key_str = "a419b0636b7054aaapi_keyfa1984bd2ecc877d76064baf7e6cc53dformatjsonuserid74805625@N06"
    val key_str = "a419b0636b7054aa"
    val key = new crypto.spec.SecretKeySpec(bytes(key_str), SHA1)
    val sig = {
      val mac = crypto.Mac.getInstance(SHA1)
      mac.init(key)

      new String(Request.encode_base64(mac.doFinal(bytes(message))))
    }
    System.out.println("Sign "+URLEncoder.encode (sig,"UTF-8"))


  }         */
}
