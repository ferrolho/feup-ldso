package models

import play.api.libs.json.Json

case class Artist(id: Long, name: String)

object Artist {
  implicit val artistFormat = Json.format[Artist]
}
