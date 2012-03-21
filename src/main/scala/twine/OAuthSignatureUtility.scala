package twine
//import com.sun.deploy.net.URLEncoder
import dispatch.Request
import javax.crypto
import org.apache.http.protocol.HTTP._
import collection.mutable.HashMap
import dispatch.{url,Http}


class OAuthSignatureUtility(fileUrl:String){

def signaturedURL(httpMethod:String, apiEndPoint:String, parameterMap:HashMap[String, String])  ={
  var sortedParameters="";
//  parameterMap.toList sortBy ( _._1 ) foreach (x => x match{case(a,b)=> println (a+" = "+b)})
  parameterMap.toList sortBy ( _._1 ) foreach (x => x match{case(a,b)=>sortedParameters+="&"+a+"="+URLEncoder.encode (b,"UTF-8")})
  sortedParameters=sortedParameters.substring(1,sortedParameters.length())
  apiEndPoint + "?" + sortedParameters + "&oauth_signature=" + getSignature (httpMethod,apiEndPoint,sortedParameters);
}



def getSignature(httpMethod:String, apiEndPoint:String, parameters:String):String=
{
  val encodedEndPoints= URLEncoder.encode(apiEndPoint,"UTF-8")
  val encodedParameters=URLEncoder.encode(parameters,"UTF-8")
  val baseString=httpMethod.toUpperCase+"&"+encodedEndPoints+"&"+encodedParameters
 // System.out.println("Base String --->  "+baseString)
  val SHA1 = "HmacSHA1"
  // val key_str = "a419b0636b7054aaapi_keyfa1984bd2ecc877d76064baf7e6cc53dformatjsonuserid74805625@N06"
  val key_str = properties.getValue("oauth_consumer_secret") +"&"+properties.getValue("oauth_token_secret")

  val key = new crypto.spec.SecretKeySpec(bytes(key_str), SHA1)
  val sig = {
    val mac = crypto.Mac.getInstance(SHA1)
    mac.init(key)

    new String(Request.encode_base64(mac.doFinal(bytes(baseString))))
  }
  var sign=URLEncoder.encode (sig,"UTF-8")

  sign
}

private def bytes(str: String) = str.getBytes(UTF_8)

  var properties: PropertyReaderWriter = null
  properties=new PropertyReaderWriter(fileUrl)
}

object OAuthSignatureUtility
{
  def unSignedResponce(req:String)
  {
    val http=new Http
    val reqURL= url(req)
    
  }
}



