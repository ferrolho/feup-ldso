package models

/**
 * Created by Lycantropus on 11-10-2015.
 */
import play.api.libs.json.Json

case class Task(id: Long, description: String, date: String )

  object Task {
    implicit val taskFormat = Json.format[Task]
  }


