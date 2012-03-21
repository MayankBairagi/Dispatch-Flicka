package twine

import twine.URLEncoder;
import xml.Elem
 import java.awt.{TextArea,TextField}
import dispatch._
import collection.immutable.HashMap


object disp{
  val windowSize = 100
  val delay = 1000

 // val artist = args.mkString(" ")


 /* def main(args:Array[String])
  {
  val http = new Http
    val params = HashMap("method" -> "flickr.people.getPublicPhotos", "api_key" -> "fa1984bd2ecc877d76064baf7e6cc53d","user_id"->"74805625@N06", "User-Agent" -> "Virginia/1.0")
    //val api = :/("musicbrainz.org") / "ws/2/artist/" <:< Map("User-Agent" -> "Virginia/1.0")
    val baseURL="api.flickr.com/services/rest/";
    val request=baseURL+""+para2URL(params)

    System.out.println("Request URL = "+request)

    val req = url("http://"+request)
  //  val api = :/("api.flickr.com") /"services/rest/?method=flickr.people.getPublicPhotos&api_key=fa1984bd2ecc877d76064baf7e6cc53d&user_id=74805625@N06" <:< Map("User-Agent" -> "Virginia/1.0") //as_str
     System.out.println("Paraxxx ==  "+para2URL(params) )

    System.out.println("Encoded xx  "+req.encode_base64("sdfssdsdssdfsdfsdffdfs&sdfsfsfsdfsfsdfd=sfsdfsdfsdfsdfsdfdsf&sfsdfsdfsdfsdfsdfsdfsdf".getBytes))
    //URLEncoder.encode("d95slrQCE0OrwGmUtnIkUJTsgYk=")
        val utf= URLEncoder.encode("http://photos.example.net/photos","UTF-8");
    System.out.println("uuuTf = "+utf)
    
    
  //  val result = http(api <<? params <> identity)
    val h=new Http
    //val result = h(api <<? params <> identity)
    val handler = req >>> System.out
    h(handler)
  }     */
  
  def para2URL(para:HashMap[String,String]):String=
  {
    var paraString="?";
     para foreach (x => x match{case(a,b)=>paraString+="&"+a+"="+b})
    paraString
  }

}