package dal

import javax.inject.{Inject, Singleton}

import models.Album
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile

import scala.concurrent.{Future, ExecutionContext}

@Singleton
class AlbumRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)
                               (implicit ec: ExecutionContext) {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import driver.api._

  private val albums = TableQuery[Albums]

  def all: Future[Seq[Album]] = db.run {
    albums.result
  }

  def create(name: String, description: String): Future[Album] = db.run {
    (albums.map(p => (p.name, p.description))
      returning albums.map(_.id)

      into ((nameDescription, id) => Album(id, nameDescription._1, nameDescription._2))
      ) +=(name, description)
  }

  // Albums table
  private class Albums(tag: Tag) extends Table[Album](tag, "albums") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def description = column[String]("description")

    def * = (id, name, description) <>((Album.apply _).tupled, Album.unapply)
  }

}
