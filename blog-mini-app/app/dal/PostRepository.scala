package dal

import javax.inject.{ Inject, Singleton }
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile

import models.Post

import scala.concurrent.{ Future, ExecutionContext }

/**
 * A repository for posts.
 *
 * @param dbConfigProvider The Play db config provider. Play will inject this for you.
 */
@Singleton
class PostRepository @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {

  // We want the JdbcProfile for this provider
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  // These imports are important, the first one brings db into scope, which will let you do the actual db operations.
  // The second one brings the Slick DSL into scope, which lets you define the table and other queries.
  import dbConfig._
  import driver.api._

  /**
   * Here we define the table. It will have a name of post
   */
  private class PostTable(tag: Tag) extends Table[Post](tag, "POST") {

    /** The ID column, which is the primary key, and auto incremented */
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    /** The subject column */
    def subject = column[String]("subject")

    /** The description column */
    def description = column[String]("description")

    /**
     * This is the tables default "projection".
     *
     * It defines how the columns are converted to and from the Post object.
     *
     * In this case, we are simply passing the id, subject and description parameters to the Post case classes
     * apply and unapply methods.
     */
    def * = (id, subject, description) <> ((Post.apply _).tupled, Post.unapply)
  }

  /**
   * The starting point for all queries on the posts table.
   */
  private val post = TableQuery[PostTable]

  /**
   * Create a post with the given subject and description.
   *
   * This is an asynchronous operation, it will return a future of the created post, which can be used to obtain the
   * id for that post.
   */
  def create(subject: String, description: String): Future[Post] = db.run {
    // We create a projection of just the name and age columns, since we're not inserting a value for the id column
    (post.map(p => (p.subject, p.description))
      // Now define it to return the id, because we want to know what id was generated for the person
      returning post.map(_.id)
      // And we define a transformation for the returned value, which combines our original parameters with the
      // returned id
      into ((params, id) => Post(id, params._1, params._2))
      // And finally, insert the post into the database
      ) += (subject, description)
  }

  /**
   * List all the people in the database.
   */
  def list(): Future[Seq[Post]] = db.run {
    post.result
  }
}
