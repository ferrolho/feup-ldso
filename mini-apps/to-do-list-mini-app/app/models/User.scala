package models

/**
 * Created by Lycantropus on 12-10-2015.
 */

import play.api.libs.json.Json


case class User (id: Long, name: String )

  object User{
    implicit val userFormat = Json.format[User]
  }
