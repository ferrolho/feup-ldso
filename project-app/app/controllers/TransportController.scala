package controllers

import javax.inject.Inject

import com.mohiva.play.silhouette.api.{Environment, Silhouette}
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import models.User
import play.api.i18n.MessagesApi

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 *
 * @param messagesApi
 * @param env
 */
class TransportController @Inject()(
                                     val messagesApi: MessagesApi,
                                     val env: Environment[User, CookieAuthenticator]
                                     )
  extends Silhouette[User, CookieAuthenticator] {

  def index = SecuredAction.async { implicit request =>
    Future.successful(Ok(views.html.transports.index(request.identity)))
  }
}
