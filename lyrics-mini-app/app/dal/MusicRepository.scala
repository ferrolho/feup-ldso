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

  private val musics = TableQuery[Musics]

  private val queryById = Compiled(
    (id: Rep[Long]) => musics.filter(_.id === id))

  def lookup(id: Long): Future[Music] = db.run {
    queryById(id).result.head
  }

  def all: Future[Seq[Music]] = db.run {
    musics.result
  }

  def update(music: Music) = db.run {
    queryById(music.id).update(music)
  }

  def delete(id: Long) = db.run {
    queryById(id).delete
  }

  def create(title: String, lyrics: String, year: Int): Future[Music] = db.run {
    (musics.map(m => (m.title, m.lyrics, m.year))
      returning musics.map(_.id)

      into ((params, id) => Music(id, params._1, params._2, params._3))
      ) +=(title, lyrics, year)
  }

  // Musics table
  private class Musics(tag: Tag) extends Table[Music](tag, "musics") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def title = column[String]("title")

    def lyrics = column[String]("lyrics")

    def year = column[Int]("year")

    def * = (id, title, lyrics, year) <>((Music.apply _).tupled, Music.unapply)
  }

}
