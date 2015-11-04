package dal

import javax.inject.{ Inject, Singleton }
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile

import models.User

import scala.concurrent.{ Future, ExecutionContext }


@Singleton
class UserRepository @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import driver.api._

  private class UserTable(tag: Tag) extends Table[User](tag, "users") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def username = column[String]("username")

    def password = column[String]("password")

    def * = (id, username, password) <> ((User.apply _).tupled, User.unapply)
  }

  private val user = TableQuery[UserTable]

  def create(username: String, password: String): Future[User] = db.run {
    (user.map(u => (u.username, u.password))
      returning user.map(_.id)
      into ((params, id) => User(id, params._1, params._2))
      ) += (username, password)
  }
}