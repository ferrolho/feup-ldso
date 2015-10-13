package dal

/**
 * Created by Lycantropus on 12-10-2015.
 */

import models.User
import javax.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile



import scala.concurrent.{Future, ExecutionContext}

class UserRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)
                              (implicit ec: ExecutionContext)
{

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import driver.api._


  private val users = TableQuery[Users]

  private val queryById = Compiled(
    (id: Rep[Long]) => users.filter(_.id === id)
  )

  def lookup(id: Long): Future[User] = db.run
  {
    queryById(id).result.head
  }

  def all: Future[Seq[User]] = db.run
  {
    users.result
  }

  def update(user: User) = db.run
  {
    queryById(user.id).update(user)
  }

  def delete (id: Long) = db.run
  {
    queryById(id).delete
  }

  private class Users(tag: Tag) extends Table[User](tag, "tasks") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def * = (id, name) <>((User.apply _).tupled, User.unapply)

  }
}
