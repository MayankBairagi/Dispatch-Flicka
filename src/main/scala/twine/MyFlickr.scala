package twine
import collection.mutable.HashMap
import net.liftweb.json._
import dispatch.url
import java.io.{OutputStream,InputStream,BufferedOutputStream,FileOutputStream}
import java.net.{HttpURLConnection,URL}


object MyFlickr {
  def main(args: Array[String]): Unit = {
    try {
      var flickr = new MyFlickr
      OAuthTokensEngine.accessTokens()
      flickr.menu()
    }
    catch {
      case e: Exception => {
        e.printStackTrace
      }
    }
    System.exit(0)
  }
}

class MyFlickr {

  def menu() {
    var in = "";
    while (in != "9") {
      System.out.println("Please Select the option  \n 1.Check the method  \n 2. Download the Picture  \n  3. Exit ");
      Thread.sleep(5000);
      in = Console.readLine();

      var map=new HashMap[String, String]

      val choice = in match{
        case "1"=>
        {
          map= OAuthTokensEngine.activeParameters(OAuthTokensEngine.defaultOAuthParameters)
          
          System.out.println("Please Enter the Request Method. Eg. flickr.contacts.getList")
          val method=Console.readLine().trim()
          map+=( "method"->method)
         var isPara="y"
         while(isPara.equals("y")){
          System.out.println("Add Additional Parameter with Request (Press y for yes and n for no")
              isPara=Console.readLine().trim()
             
           if(isPara.equals("y"))
           {
             System.out.println("Enter the Parameter Name.  Eg. photo_id ")
             val name=Console.readLine()
             
             System.out.println("Enter the Value")
             val value=Console.readLine()

             map+=(name -> value)
             }
           }
          System.out.println("Please Enter the Type(GET or POST) of Http Request Method. Eg.  GET ")
          val getORpost = Console.readLine().trim()

          val resp=ResponseFlika.getResponseAsString(getORpost,map,"http://api.flickr.com/services/rest/",fileURL)
        }

        case "2"=>
          {

            try {
              var url:URL = null
              System.out.println("To Download the photo, some parameters like farm-id,server-id,id,secret are required. These values are returned by the flickr.photos.getInfo method and by any method that returns a list of photos \n \n")
              System.out.println("Enter the Farm-ID")
              val farmID=Console.readLine().trim()

              System.out.println("Enter the Server-ID")
              val serverID=Console.readLine().trim()

              System.out.println("Enter the Photo ID")
              val photoID=Console.readLine().trim()

              System.out.println("Enter the Secret")
              val secret=Console.readLine().trim()

              System.out.println("Enter the Photo Extension like jpg|gif|png .   Eg. jpg")
              val ext=Console.readLine().trim()

              url = new URL("http://farm"+farmID+".staticflickr.com/"+serverID+"/"+photoID+"_"+secret+"."+ext+"")
             // url=new URL("http://farm8.staticflickr.com/7066/6822654366_334f27281b.jpg")  ;

              UrlDownload.fileDownload("http://farm8.staticflickr.com/7066/6822654366_334f27281b_m.jpg","");

            }
            catch {
              case e:Exception => println(e.printStackTrace())
            }

          }
      }

    /*    case "2"=>   {
          map= OAuthTokensEngine.activeParameters(OAuthTokensEngine.defaultOAuthParameters)
          map+=( "method"->"flickr.favorites.getList")
          val resp=ResponseFlika.getResponseAsString("GET",map,"http://api.flickr.com/services/rest/",fileURL)
        }

        case "3"=>   {
          map= OAuthTokensEngine.activeParameters(OAuthTokensEngine.defaultOAuthParameters)
          map+=( "method"->"flickr.people.getPhotos",
                 "user_id"->properties.getValue("user_nsid") )
          val resp=ResponseFlika.getResponseAsString("GET",map,"http://api.flickr.com/services/rest/",fileURL)
        }

    }
                        */



    }
  }

  val fileURL=Config.propertyFileURL
  var properties: PropertyReaderWriter = null
  properties=new PropertyReaderWriter(fileURL)

  var map=new HashMap[String, String]

 
}