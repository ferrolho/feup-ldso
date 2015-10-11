package dal

/**
 * Created by Lycantropus on 11-10-2015.
 */

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile

import models.Task

import scala.concurrent.{Future, ExecutionContext}

@Singleton
class TaskRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)
(implicit ec: ExecutionContext) {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import driver.api._

  private class TaskTables(tag: Tag) extends Table[Task](tag, "tasks") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def description = column[String]("decription")

    def date = column[String] ("time")

    def * = (id, description, date) <>((Task.apply _).tupled, Task.unapply)

  }

  private val tasks = TableQuery[TaskTables]

  def create(description: String, date: String): Future[Task] = db.run {
    (tasks.map(p => (p.description, p.date))
      returning tasks.map(_.id)

      into ((descriptiondate, id) => Task(id, description, date))
      ) +=(description, date)


    def list(): Future[Seq[Task]] = db.run {
      tasks.result
    }

  }
}
