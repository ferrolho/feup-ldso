package models

import play.api.libs.json.Json

case class Music(id: Long, albumId: Long, title: String, lyrics: String)

object Music {
  implicit val musicFormat = Json.format[Music]
}
