package dal

/**
 * Created by Lycantropus on 11-10-2015.
 */
import models.Task
import javax.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile



import scala.concurrent.{Future, ExecutionContext}


class TaskRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)
(implicit ec: ExecutionContext) {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import driver.api._

  private val tasks = TableQuery[Tasks]

  private val queryById = Compiled(
    (id: Rep[Long]) => tasks.filter(_.id===id)
  )

  /*private val queryByDate = Compiled(
    (id: Rep[Long]) => tasks.filter(_.date === date)
  )
*/
  def lookup(id: Long): Future[Seq[Task]] = db.run
  {
    queryById(id).result
  }

  def all: Future[Seq[Task]] = db.run
  {
    tasks.result
  }

  def update(task: Task) = db.run
  {
    queryById(task.id).update(task)
  }

  def delete (id: Long) = db.run
  {
    queryById(id).delete
  }



  def create(description: String, date: String): Future[Task] = db.run {
    (tasks.map(t => (t.description, t.date))
      returning tasks.map(_.id)

      into ((params, id) => Task(id, params._1, params._2))
      ) +=(description, date)
  }

  private class Tasks(tag: Tag) extends Table[Task](tag, "tasks") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def description = column[String]("decription")

    def date = column[String] ("date")

    def * = (id, description, date) <>((Task.apply _).tupled, Task.unapply)

  }




}
