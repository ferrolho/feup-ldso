package controllers

import javax.inject.Inject

import dal.MusicRepository
import models.Music
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.{Future, ExecutionContext}

class MusicController @Inject()(repo: MusicRepository, val messagesApi: MessagesApi)
                               (implicit ec: ExecutionContext) extends Controller with I18nSupport {

  def index = Action {
    Ok(views.html.musics.index(musicForm))
  }

  def show(id: Long) = Action.async { implicit request =>
    repo.lookup(id).map { music =>
      Ok(views.html.musics.show(music))
    }
  }

  val musicForm: Form[CreateMusicForm] = Form {
    mapping(
      "albumId" -> longNumber,
      "title" -> nonEmptyText,
      "lyrics" -> nonEmptyText
    )(CreateMusicForm.apply)(CreateMusicForm.unapply)
  }

  def addMusic = Action.async { implicit request =>
    musicForm.bindFromRequest.fold(
      errorForm => {
        Future.successful(Ok(views.html.musics.index(errorForm)))
      },
      music => {
        repo.create(music.albumId, music.title, music.lyrics).map { _ =>
          Redirect(routes.MusicController.index)
        }
      }
    )
  }

  def getMusics = Action.async {
    repo.all.map { musics =>
      Ok(Json.toJson(musics))
    }
  }

}

case class CreateMusicForm(albumId: Long, title: String, lyrics: String)
