package controllers

import javax.inject.Inject

import dal.AlbumRepository
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.{Future, ExecutionContext}

class AlbumController @Inject()(repo: AlbumRepository, val messagesApi: MessagesApi)
                               (implicit ec: ExecutionContext) extends Controller with I18nSupport {

  def index = Action {
    Ok(views.html.albums.index(albumForm))
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
    repo.list().map { albums =>
      Ok(Json.toJson(albums))
    }
  }

}

case class CreateAlbumForm(name: String, description: String)
