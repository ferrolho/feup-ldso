package controllers

import java.util.UUID
import javax.inject.Inject

import com.mohiva.play.silhouette.api.{Environment, Silhouette}
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import forms.SupplyForm
import models.services.SupplyServiceImpl
import models.{Supply, User}
import play.api.i18n.MessagesApi
import play.api.libs.concurrent.Execution.Implicits._

import scala.concurrent.Future

class SuppliesController @Inject()(
                                    val messagesApi: MessagesApi,
                                    val env: Environment[User, CookieAuthenticator],
                                    supplyService: SupplyServiceImpl)
  extends Silhouette[User, CookieAuthenticator] {

  def index = SecuredAction.async { implicit request =>
    Future.successful(Ok(views.html.supplies.index(request.identity, SupplyForm.form)))
  }

  /**
   * Submits a resource supply.
   *
   * @return The result to display.
   */
  def submitSupplyOffer = SecuredAction.async { implicit request =>
    SupplyForm.form.bindFromRequest.fold(
      form => Future.successful(BadRequest(views.html.supplies.index(request.identity, form))),
      data => {
        val supply = Supply(
          id = UUID.randomUUID(),
          userID = request.identity.userID,
          resource = data.resource,
          amount = data.amount
        )
        for {
          supply <- supplyService.save(supply.copy())
        } yield Redirect(routes.Application.index())
      }
    )
  }

}
