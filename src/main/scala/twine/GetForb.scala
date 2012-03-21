package twine

import net.liftweb.json._
import collection.mutable.HashMap
import dispatch.{url, Request, Http}
 import dispatch.thread._
import dispatch.mime._
import java.lang.System
import java.io.{FileInputStream, File}

/**
 * Created by IntelliJ IDEA.
 * User: Astar
 * Date: 3/4/12
 * Time: 4:02 PM
 * To change this template use File | Settings | File Templates.
 */


object ResponseFlika {
  var response=new String
  
  def getResponseAsString(httpMethod:String,map:HashMap[String, String],apiEndPoint:String,fileURL:String):String={

//  var SignedURL=new twine.OAuthSignatureUtility(fileURL).signaturedURL(httpMethod,apiEndPoint,map)
    var SignedURL=SignFactory.signedURL(httpMethod,apiEndPoint,map)
 // map foreach (x=> println(x._1+"  ---  "+x._2))
//  System.out.println("Signed Request URL 111  "+SignedURL)
  val h=new Http with Safety

  var req=url(SignedURL)
    
  val handler=req as_str

  try{
  response= h(handler)
  }catch{
    case e: Exception => {
     // Thread.sleep(3000)
      map.update("oauth_nonce" ,(System.nanoTime).toString )
      var SignedURL=new twine.OAuthSignatureUtility(fileURL).signaturedURL(httpMethod,apiEndPoint,map)
    //  System.out.println("Signed Request URL 222  "+SignedURL)
    //  map foreach (x=> println(x._1+"  ---  "+x._2))
   //   System.out.println("The Alternative way ###########################")

  //    System.out.println("Signed Request URL "+SignedURL)
      val h=new Http with Safety

      var req=url(SignedURL)
      if(httpMethod.equals("POST")) {
        req= url(SignedURL) POST
      }
      val handler=req as_str

      try{
      response=h(handler)
      }catch{
        case e: Exception =>{
          System.out.println("System is unable to process request please try again")
        }
      }





    }
  }


  try{
  val json=parse(response)
  System.out.println("Response   Formated   "+ pretty (render(json)))
  } catch {
    case e: Exception => {
      System.out.println("Response is not in standard json format")
      System.out.println("Response   Simple   "+response )
    }
  }
  
   h.shutdown()
  h.shutdownClient()
  response
  }

  def getResponseAsMap(httpMethod:String,map:HashMap[String, String],apiEndPoint:String,fileURL:String):HashMap[String, String]=
  {
    getResponseAsString(httpMethod,map,apiEndPoint,fileURL)
    keyValueMapDecomposer(response)
  }


  def as_str=response

  def keyValueMapDecomposer(responce:String ):HashMap[String, String]=
  {
    var map=new HashMap[String, String]
    responce.split("&").map(_.split("=")).foreach(a=>if(a.size==2){ map += a(0) -> a(1)} else map+= a(0)->"")
    map
  }

}
