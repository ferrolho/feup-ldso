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

  private val queryById = Compiled(
    (id: Rep[Long]) => albums.filter(_.id === id))

  def lookup(id: Long): Future[Album] = db.run {
    queryById(id).result.head
  }

  def all: Future[Seq[Album]] = db.run {
    albums.result
  }

  def create(name: String, year: Int, description: String): Future[Album] = db.run {
    (albums.map(p => (p.name, p.year, p.description))
      returning albums.map(_.id)

      into ((params, id) => Album(id, params._1, params._2, params._3))
      ) +=(name, year, description)
  }

  // Albums table
  private class Albums(tag: Tag) extends Table[Album](tag, "albums") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def year = column[Int]("year")

    def description = column[String]("description")

    def * = (id, name, year, description) <>((Album.apply _).tupled, Album.unapply)
  }

}
