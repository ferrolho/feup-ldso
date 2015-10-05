package controllers

import javax.inject.Inject

import dal.ArtistRepository
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.{MessagesApi, I18nSupport}
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.{Future, ExecutionContext}

class ArtistController @Inject()(repo: ArtistRepository, val messagesApi: MessagesApi)
                                (implicit ec: ExecutionContext) extends Controller with I18nSupport {

  def index = Action {
    Ok(views.html.artists.index(artistForm))
  }

  /**
   * The mapping for the artist form.
   */
  val artistForm: Form[CreateArtistForm] = Form {
    mapping(
      "name" -> nonEmptyText
    )(CreateArtistForm.apply)(CreateArtistForm.unapply)
  }

  /**
   * The add artist action.
   *
   * This is asynchronous, since we're invoking the asynchronous methods on PersonRepository.
   */
  def addArtist = Action.async { implicit request =>
    // Bind the form first, then fold the result, passing a function to handle errors, and a function to handle succes.
    artistForm.bindFromRequest.fold(
      // The error function. We return the index page with the error form, which will render the errors.
      // We also wrap the result in a successful future, since this action is synchronous, but we're required to return
      // a future because the artist creation function returns a future.
      errorForm => {
        Future.successful(Ok(views.html.artists.index(errorForm)))
      },
      // There were no errors in the form, so create the artist.
      artist => {
        repo.create(artist.name).map { _ =>
          // If successful, we simply redirect to the index page.
          Redirect(routes.ArtistController.index)
        }
      }
    )
  }

  /**
   * A REST endpoint that gets all the artists as JSON.
   */
  def getArtists = Action.async {
    repo.list().map { artists =>
      Ok(Json.toJson(artists))
    }
  }

}

/**
 * The create artist form.
 *
 * Generally for forms, you should define separate objects to your models, since forms very often need to present data
 * in a different way to your models.  In this case, it doesn't make sense to have an id parameter in the form, since
 * that is generated once it's created.
 */
case class CreateArtistForm(name: String)
