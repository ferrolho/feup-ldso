package controllers

import javax.inject.Inject

import dal.{MusicRepository, AlbumRepository}
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.{Future, ExecutionContext}

class AlbumController @Inject()(repo: AlbumRepository, musicRepo: MusicRepository, val messagesApi: MessagesApi)
                               (implicit ec: ExecutionContext) extends Controller with I18nSupport {

  def index = Action.async {
    repo.all map { albums =>
      Ok(views.html.albums.index(albumForm, albums))
    }
  }

  def show(id: Long) = Action.async {
    // Creating the Futures before the for comprehension - this way the two calls can happen in parallel.
    val fAlbum = repo.lookup(id)
    val fMusics = musicRepo.fromAlbum(id)

    for {album <- fAlbum; musics <- fMusics}
      yield Ok(views.html.albums.show(album, musics))
  }

  val albumForm: Form[CreateAlbumForm] = Form {
    mapping(
      "name" -> nonEmptyText,
      "description" -> text
    )(CreateAlbumForm.apply)(CreateAlbumForm.unapply)
  }

  def addAlbum = Action.async { implicit request =>
    albumForm.bindFromRequest.fold(
      errorForm => {
        Future.successful(Ok(views.html.albums.index(errorForm)))
      },
      album => {
        repo.create(album.name, album.description).map { _ =>
          Redirect(routes.AlbumController.index)
        }
      }
    )
  }

  def getAlbums = Action.async {
    repo.all.map { albums =>
      Ok(Json.toJson(albums))
    }
  }

}

case class CreateAlbumForm(name: String, description: String)
