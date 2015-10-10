package models

import play.api.libs.json.Json

case class Album(id: Long, name: String, year: Int, description: String)

object Album {
  implicit val albumFormat = Json.format[Album]
}
