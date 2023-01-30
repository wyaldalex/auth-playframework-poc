package models

import play.api.libs.json.Json

case class Post(id: Int, content: String)

object Post {
  implicit val format = Json.format[Post]
}
