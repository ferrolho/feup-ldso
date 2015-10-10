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

  private val queryByAlbumId = Compiled(
    (id: Rep[Long]) => musics.filter(_.albumId === id))

  def lookup(id: Long): Future[Music] = db.run {
    queryById(id).result.head
  }

  def fromAlbum(albumId: Long): Future[Seq[Music]] = db.run {
    queryByAlbumId(albumId).result
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

  def create(albumId: Long, title: String, lyrics: String): Future[Music] = db.run {
    (musics.map(m => (m.albumId, m.title, m.lyrics))
      returning musics.map(_.id)

      into ((params, id) => Music(id, params._1, params._2, params._3))
      ) +=(albumId, title, lyrics)
  }

  // Musics table
  private class Musics(tag: Tag) extends Table[Music](tag, "musics") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def albumId = column[Long]("album_id")

    def title = column[String]("title")

    def lyrics = column[String]("lyrics")

    def * = (id, albumId, title, lyrics) <>((Music.apply _).tupled, Music.unapply)
  }

}
