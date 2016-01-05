package controllers

import javax.inject.Inject

import com.mohiva.play.silhouette.api.{LogoutEvent, Silhouette, Environment}
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import com.mohiva.play.silhouette.impl.providers.SocialProviderRegistry
import forms._
import models.User
import models.services.CountryService
import play.api.Play
import play.api.Play.current
import play.api.libs.json._
import play.api.i18n.MessagesApi
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.io.Source

class Application @Inject()(
                             val messagesApi: MessagesApi,
                             val env: Environment[User, CookieAuthenticator],
                             socialProviderRegistry: SocialProviderRegistry,
                             countryService: CountryService)
  extends Silhouette[User, CookieAuthenticator] {

  /**
   * Handles the index action.
   *
   * @return The result to display.
   */
  def index = SecuredAction.async { implicit request =>
    countryService.all.flatMap { countries =>
      Future.successful(Ok(views.html.home(request.identity, countries)))
    }
  }

  /**
   * Handles the Sign In action.
   *
   * @return The result to display.
   */
  def signIn = UserAwareAction.async { implicit request =>
    request.identity match {
      case Some(user) => Future.successful(Redirect(routes.Application.index()))
      case None => Future.successful(Ok(views.html.auth.signIn(SignInForm.form, socialProviderRegistry)))
    }
  }

  /**
   * A REST endpoint that gets all the countries and their respective cities as JSON.
   */
  def getCountryToCitiesJSON = UserAwareAction.async {
    val source: String = Source.fromURL(Play.resource("countriesToCities.json").get).getLines.mkString
    val json: JsValue = Json.parse(source)

    Future.successful(Ok(json))
  }

  /**
   * Handles the Sign Up action.
   *
   * @return The result to display.
   */
  def signUp = UserAwareAction.async { implicit request =>
    countryService.all.flatMap { countries =>
      val countrySelectOptions = countries.map { model => (model.id.toString, model.name) }

      request.identity match {
        case Some(user) => Future.successful(Redirect(routes.Application.index()))
        case None => Future.successful(Ok(views.html.auth.signUp(SignUpForm.form, countrySelectOptions)))
      }
    }
  }

  /**
   * Handles the Sign Out action.
   *
   * @return The result to display.
   */
  def signOut = SecuredAction.async { implicit request =>
    val result = Redirect(routes.Application.index())
    env.eventBus.publish(LogoutEvent(request.identity, request, request2Messages))

    env.authenticatorService.discard(request.authenticator, result)
  }

  def playInfo = Action {
    Ok(views.html.playInfo("Your application is ready."))
  }

}
