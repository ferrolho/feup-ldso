package models

import play.api.libs.json.Json

case class Music(id: Long, albumId: Long, title: String, lyrics: String, year: Int)

object Music {
  implicit val musicFormat = Json.format[Music]
}
