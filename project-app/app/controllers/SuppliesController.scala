package controllers

import java.util.UUID
import javax.inject.Inject

import com.mohiva.play.silhouette.api.{Environment, Silhouette}
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import forms.SupplyForm
import models.services.SupplyService
import models.{Supply, User}
import play.api.i18n.MessagesApi

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * The supplies controller.
 *
 * @param messagesApi The Play messages API.
 * @param env The Silhouette environment.
 * @param supplyService The supply service implementation.
 */
class SuppliesController @Inject()(
                                    val messagesApi: MessagesApi,
                                    val env: Environment[User, CookieAuthenticator],
                                    supplyService: SupplyService)
  extends Silhouette[User, CookieAuthenticator] {

  def index = SecuredAction.async { implicit request =>
    supplyService.all map { supplies =>
      Ok(views.html.supplies.index(request.identity, SupplyForm.form, supplies))
    }
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
        } yield Redirect(routes.SuppliesController.index())
      }
    )
  }

}
