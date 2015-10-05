package dal

import javax.inject.Inject

import models.Music
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile

import scala.concurrent.{Future, ExecutionContext}

class MusicRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)
                               (implicit ec: ExecutionContext) {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import driver.api._

  private class MusicsTable(tag: Tag) extends Table[Music](tag, "musics") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def title = column[String]("title")

    def lyrics = column[String]("lyrics")

    def year = column[Int]("year")

    def * = (id, title, lyrics, year) <>((Music.apply _).tupled, Music.unapply)
  }

  private val musics = TableQuery[MusicsTable]

  def create(title: String, lyrics: String, year: Int): Future[Music] = db.run {
    (musics.map(m => (m.title, m.lyrics, m.year))
      returning musics.map(_.id)

      into ((stuff, id) => Music(id, stuff._1, stuff._2, stuff._3))

      ) +=(title, lyrics, year)
  }

  def list(): Future[Seq[Music]] = db.run {
    musics.result
  }

}
