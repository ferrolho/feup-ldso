package models

import play.api.libs.json.Json

case class Photo(id: Long, name: String, url: String)

object Photo {
  implicit val photoFormat = Json.format[Photo]
}
