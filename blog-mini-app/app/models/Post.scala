package models

import play.api.libs.json._

case class Post(id: Long, subject: String, description: String)

object Post {

  implicit val PostFormat = Json.format[Post]
}