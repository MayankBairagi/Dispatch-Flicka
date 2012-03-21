/* package twine

import dispatch._
import scala.xml._

case class User(nsid: String, name: String)

case class Person(
                   nsid: String,
                   username: String,
                   realname: String,
                   location: String,
                   photosurl: String,
                   profileurl: String
                   )

case class Photo(
                  id: String,
                  owner: String,
                  farm: String,
                  server: String,
                  secret: String,
                  title: String
                  )

sealed class Error(code: Int, description: String) {
  override def toString = "Error[" + code + "]: " + description
}

case class InvalidApiKey() extends Error(100, "Invalid API Key")

case class ServiceUnavailable() extends Error(105, "Service currently unavailable")

case class FormatNotFound() extends Error(111, """Format "xxx" not found""")

case class MethodNotFound() extends Error(112, """Method "xxx" not found""")

object Implicits {
  implicit def nodeSeq2String(n: NodeSeq) = n.text

  implicit def str2Int(s: String) = s.toInt
}

object Flickr {
  def apply(key: String) = new Flickr(key)

  val www = :/("www.flickr.com")
  val photos = www / "photos"

  val services = :/("api.flickr.com") / "services"

  val rest = services / "rest" / ""

  /*
    def people = www / "people" / _

    def photostream = photos / _
    def photo(user: String, photo: String) = photos / user / photo
    def allSets(user: String) = photos / user / "sets"
    def photoSet(user: String, set: String) = photos / user / "sets" / set

    def photoUrl(farmId: String, serverId: String, photoId: String, secret: String) = (:/("farm" + farmId + ".static.flickr.com") / serverId / (photoId + "_" + secret +  ".jpg")).to_uri
  */

}

case class ApiKey(key: String)

class Method[T](name: String, block: Elem => T) {
  def apply(params: (String, String)*)(implicit api_key: ApiKey) = new QueryBuilder(block, Map(params: _*)).method(name).api(api_key.key)

  class QueryBuilder(block: Elem => T, params: Map[String, String]) extends Builder[Handler[Either[T, Error]]] {
    def param(key: String)(value: String) = new QueryBuilder(block, params + (key -> value))

    val method = param("method") _
    val api = param("api_key") _

    def product = Flickr.rest <<? params.toTraversable <> {
      node =>
        import Implicits._
        node \ "_" head match {
          case e: Elem if e.label == "err" => Right(new Error(e \ "@code" text, e \ "@msg"))
          case e: Elem => Left(block(e))
        }
    }
  }

}

class Flickr(apiKey: String) {
  implicit val api_key = ApiKey(apiKey)

  import Implicits._

  object People {

    def findByUsername(name: String) = new Method("flickr.people.findByUsername", user => User(user \ "@nsid", name))("username" -> name)

    def findByEmail(email: String) = new Method("flickr.people.findByEmail", user => User(user \ "@nsid", user \ "username"))("find_email" -> email)

    def getInfo(nsid: String) = new Method("flickr.people.getInfo",
      p => Person(p \ "@nsid",
        p \ "username",
        p \ "realname",
        p \ "location",
        p \ "photosurl",
        p \ "profileurl")
    )("user_id" -> nsid)
  }

  object Photos {

    def search(params: (String, Any)*) = new Method("flickr.photos.search",
      photos => photos \ "photo" map {
        p => Photo(p \ "@id",
          p \ "@owner",
          p \ "@farm",
          p \ "@server",
          p \ "@secret",
          p \ "@title")
      }
    )(params: _*)
  }

}    */