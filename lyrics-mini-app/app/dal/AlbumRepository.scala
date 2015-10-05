package dal

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile

import models.Album

import scala.concurrent.{Future, ExecutionContext}

@Singleton
class AlbumRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import driver.api._

  private class AlbumsTable(tag: Tag) extends Table[Album](tag, "albums") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def description = column[String]("description")

    def * = (id, name, description) <>((Album.apply _).tupled, Album.unapply)
  }

  private val albums = TableQuery[AlbumsTable]

  def create(name: String, description: String): Future[Album] = db.run {
    (albums.map(p => (p.name, p.description))
      returning albums.map(_.id)

      into ((nameDescription, id) => Album(id, nameDescription._1, nameDescription._2))
      // And finally, insert the person into the database
      ) +=(name, description)
  }

  def list(): Future[Seq[Album]] = db.run {
    albums.result
  }

}
