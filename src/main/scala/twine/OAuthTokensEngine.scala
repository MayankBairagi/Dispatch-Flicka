package twine

import collection.mutable.HashMap
import net.liftweb.json._

/**
 * Created by IntelliJ IDEA.
 * User: Astar
 * Date: 3/9/12
 * Time: 12:31 AM
 * To change this template use File | Settings | File Templates.
 */

object OAuthTokensEngine {

  def accessTokens()
  {

    if(is_token_alive()==false)
    {
      properties.getValue("oauth_token").equals("")
      properties.getValue("oauth_token_secret").equals("")
      properties.getValue("oauth_verifier").equals("")

      System.out.println("All the process Repeat")
      getRequestToken
      getAuthenticated
      getAccessToken
    }

  }

  def is_token_alive():Boolean=
  {
    var expire=false
    try{
      var maps=new HashMap[String, String]
      maps=activeParameters(defaultOAuthParameters)
      //(maps.remove("oauth_callback"))
      maps-=("oauth-verifier","oauth_callback")
      maps+=(
        "method"->"flickr.auth.oauth.checkToken"
        )

      if(properties.getValue("oauth_token").equals(""))  {return false}

      val resp=ResponseFlika.getResponseAsString("GET",maps,"http://api.flickr.com/services/rest/",fileURL)

      val json = parse(resp)

      val list=for { JField("stat", JString(stat)) <- json } yield stat
      val stat=list(0)
      System.out.println("Stat "+stat)
      if(stat.equals("ok"))
        expire= true
      else
        expire= false
      expire
    }catch {
      case e: Exception => {
        System.out.println("ErrorXXXXX "+expire)
        e.printStackTrace
        return expire
      }
    }
  }

  def getRequestToken
  {
    properties.setValue("oauth_token","")
    properties.setValue("oauth_token_secret","")
    properties.setValue("oauth_verifier","")
    var map=new HashMap[String, String]
    map=activeParameters(defaultOAuthParameters)
    // map+=("oauth_callback"->"oob")
    val responseMap=ResponseFlika.getResponseAsMap("GET",map,"http://www.flickr.com/services/oauth/request_token/",fileURL)

    properties.setValue("oauth_token",responseMap ("oauth_token"))
    properties.setValue("oauth_token_secret",responseMap("oauth_token_secret"))
  }

  def getAuthenticated
  {
    val authanticationURL="http://www.flickr.com/services/oauth/authorize?oauth_token="+properties.getValue("oauth_token")+"&perms=delete" ;
    val open=new OpenURI
    open.openLink(authanticationURL)
    System.out.println("Please Enter the Verifier provided by browser")
    val verifier=Console.readLine()
    properties.setValue("oauth_verifier",verifier.toString)
  }

  def getAccessToken{
    var map=new HashMap[String, String]
    map=activeParameters(defaultOAuthParameters)

    val resp=ResponseFlika.getResponseAsMap("POST",map,"http://www.flickr.com/services/oauth/access_token/",fileURL)

    properties.setValue("oauth_token",resp ("oauth_token"))
    properties.setValue("oauth_token_secret",resp("oauth_token_secret"))
    properties.setValue("user_nsid",resp("user_nsid"))
  }

  def defaultOAuthParameters:HashMap[String, String]=
  {
    val map=new HashMap[String, String]()
    val nonce = System.nanoTime.toString
    val timeStamp=(System.currentTimeMillis / 1000).toString
    map+=("oauth_consumer_key"->properties.getValue("oauth_consumer_key") ,
      "oauth_signature_method"->"HMAC-SHA1",
      "oauth_callback"->"oob",
      "oauth_timestamp"->timeStamp,
      "oauth_nonce"->nonce,
      "oauth_version"->"1.0",
      "oauth_token"->properties.getValue("oauth_token"),
      "oauth_verifier"->properties.getValue("oauth_verifier"),
      "format"->"json",
      "nojsoncallback" -> "1"
      )

    map
  }

  def activeParameters(map:HashMap[String, String]):HashMap[String, String]=
  {
    map foreach ({case(k,v)=>if(v.equals("")){ map.remove(k) ; System.out.println("Blank######   "+k+"    "+v)} })
    map
  }

  val fileURL=Config.propertyFileURL
  var properties: PropertyReaderWriter = null
  properties=new PropertyReaderWriter(fileURL)
}
