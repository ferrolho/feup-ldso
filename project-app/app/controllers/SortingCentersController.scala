package controllers

import java.util.UUID
import javax.inject.Inject

import com.mohiva.play.silhouette.api.{Environment, Silhouette}
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import forms.SortingCenterWarehouseForm
import models.services.{SortingCenterWarehouseService, SupplyService}
import models.{SortingCenterWarehouse, User}
import play.api.i18n.MessagesApi

import scala.concurrent.ExecutionContext.Implicits.global

class SortingCentersController @Inject()(
                                          val messagesApi: MessagesApi,
                                          val env: Environment[User, CookieAuthenticator],
                                          supplyService: SupplyService,
                                          sortingCenterWarehouseService: SortingCenterWarehouseService)
  extends Silhouette[User, CookieAuthenticator] {

  def index = SecuredAction.async { implicit request =>
    supplyService.allExceptByUser(request.identity.userID).map { supplies =>
      Ok(views.html.sortingCenters.index(request.identity, SortingCenterWarehouseForm.form, supplies))
    }
  }

  /**
   * Submits a resource supply.
   *
   * @return The result to display.
   */
  def acceptOffer = SecuredAction.async { implicit request =>
    SortingCenterWarehouseForm.form.bindFromRequest.fold(
      form => {
        supplyService.allExceptByUser(request.identity.userID).map { supplies =>
          BadRequest(views.html.sortingCenters.index(request.identity, form, supplies))
        }
      },
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
