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
import scala.concurrent.Future

/**
 *
 * @param messagesApi
 * @param env
 * @param supplyService
 * @param sortingCenterWarehouseService
 */
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
        // TODO delete this when error is solved
        Future.successful(Redirect(routes.Application.index()))

        supplyService.allExceptByUser(request.identity.userID).map { supplies =>
          BadRequest(views.html.sortingCenters.index(request.identity, form, supplies))
        }
      },
      data => {
        supplyService.retrieve(UUID.fromString(data.supplyID)).map { supply =>
          val offer = SortingCenterWarehouse(
            idResource = supply.id,
            idSortingCenter = UUID.randomUUID(),
            userID = request.identity.userID,
            resource = supply.resource,
            amount = supply.amount,
            inSortingCenter = false
          )

          sortingCenterWarehouseService.save(offer.copy())

          Redirect(routes.SortingCentersController.index())
        }
      }
    )
  }
}
