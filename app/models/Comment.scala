package models

import play.api.libs.json.Json

case class Comment(id: Int, postId: Int, text: String, authorName: String)

object Comment {
  implicit val format = Json.format[Comment]
}
