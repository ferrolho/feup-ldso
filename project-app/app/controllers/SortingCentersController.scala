package controllers

import java.util.UUID
import javax.inject.Inject

import com.mohiva.play.silhouette.api.{Environment, Silhouette}
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import forms.{ SortingCenterWarehouseForm}
import models.services.{SupplyService, SortingCenterWarehouseService}
import models.User
import models.SortingCenterWarehouse
import play.api.i18n.MessagesApi

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class SortingCentersController @Inject()(
                                          val messagesApi: MessagesApi,
                                          val env: Environment[User, CookieAuthenticator],
                                          supplyService: SupplyService,
                                          sortingCenterWarehouseService: SortingCenterWarehouseService)
  extends Silhouette[User, CookieAuthenticator] {

 /*def index = SecuredAction.async { implicit request =>
   supplyService.byUser(request.identity.userID).map { supplies =>
    Ok(views.html.sortingCenters.index(request.identity, SortingCenterWarehouseForm.form,supplies))
  }*/

  def index = SecuredAction.async { implicit request =>
    Future.successful(Ok(views.html.sortingCenters.index(request.identity, SortingCenterWarehouseForm.form)))
    }


  /**
   * Submits a resource supply.
   *
   * @return The result to display.
   */
  def acceptOffer = SecuredAction.async { implicit request =>
    SortingCenterWarehouseForm.form.bindFromRequest.fold(
      form => Future.successful(BadRequest(views.html.sortingCenters.index(request.identity, form))),
      data => {
        val offer = SortingCenterWarehouse(
          idResource = UUID.randomUUID(),
        idSortingCenter = UUID.randomUUID(),
          userID = request.identity.userID,
          resource = data.resource,
          amount = data.amount,
        inSortingCenter = false
        )
        for {
          offer <- sortingCenterWarehouseService.save(offer.copy())
        } yield Redirect(routes.SortingCentersController.index())
      }
    )
  }
}
