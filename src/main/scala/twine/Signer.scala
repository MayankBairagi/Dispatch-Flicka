package twine

import dispatch._
import dispatch.oauth._
import collection.mutable.HashMap

/**
 * Created by IntelliJ IDEA.
 * User: Astar
 * Date: 3/11/12
 * Time: 1:44 AM
 * To change this template use File | Settings | File Templates.
 */

object SignFactory {

  //  val uploadEndPoint = :/("api.flickr.com") / "services" / "rest"

  def signedURL(httpMethod: String, baseURL: String, userMap: HashMap[String, String]): String = {

    //      System.out.println("user Para  "+userMap("method"))
     var map=Map[String, Any]()
    
     userMap foreach (x=> if (x._1.indexOf("oauth")== -1) (map+=(x._1->x._2)))
   // map foreach (x=>println("User Par XXXX  "+x._1+" ** "+x._2))
    
    val oAuthMap = OAuth.sign(httpMethod, baseURL, map, consumer, Some(token), Some(properties.getValue("verifier")), Some(OAuth.oob))
    var oAuthParameters = ""
    var userParameters = ""

    oAuthMap foreach (x => x match {
      case (a, b) => oAuthParameters += "&" + a + "=" + URLEncoder.encode(b, "UTF-8")
    })
    map foreach (x => x match {
      case (a, b) => userParameters += "&" + a + "=" + b
    })

    var reqURL = baseURL + "?" + oAuthParameters
   // System.out.println("Upload end point " + baseURL)
    reqURL + "" + userParameters
  }

  /* def signRequest(userParams: Map[String, String], accessToken: Token, verifier: String): Map[String, String] = {
var map = userParams
map += "api_key" -> consumerKey
sign("", uploadEndPoint.toString, userParams, consumer, Some(accessToken), Some(verifier), Some(OAuth.oob))
}      */



  val fileURL = Config.propertyFileURL
  var properties: PropertyReaderWriter = null
  properties = new PropertyReaderWriter(fileURL)

  val consumer = Consumer(properties.getValue("oauth_consumer_key"), properties.getValue("oauth_consumer_secret"))
  val token = Token(properties.getValue("oauth_token"), properties.getValue("oauth_token_secret"))
}
