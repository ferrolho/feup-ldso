package dal

import javax.inject.{ Inject, Singleton }
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile

import models.Post

import scala.concurrent.{ Future, ExecutionContext }


@Singleton
class PostRepository @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import driver.api._

  private class PostTable(tag: Tag) extends Table[Post](tag, "posts") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def subject = column[String]("subject")

    def description = column[String]("description")

    def * = (id, subject, description) <> ((Post.apply _).tupled, Post.unapply)
  }

  private val post = TableQuery[PostTable]

  def create(subject: String, description: String): Future[Post] = db.run {
    (post.map(p => (p.subject, p.description))
      returning post.map(_.id)
      into ((params, id) => Post(id, params._1, params._2))
      ) += (subject, description)
  }

  def list(): Future[Seq[Post]] = db.run {
    post.result
  }
}
