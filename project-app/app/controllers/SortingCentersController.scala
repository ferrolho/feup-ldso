package controllers

import java.util.UUID
import javax.inject.Inject

import com.mohiva.play.silhouette.api.{Environment, Silhouette}
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import forms.SortingCenterWarehouseForm
import models.services.{SortingCenterWarehouseService, SupplyService}
import models.{SortingCenterWarehouse, Supply, User}
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
   * Submits a resource supply and deletes the same entry in Supply table.
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
        supplyService.retrieve(UUID.fromString(data.supplyID)).flatMap { supply =>
          val offer = SortingCenterWarehouse(
            idResource = supply.id,
            idSortingCenter = UUID.randomUUID(),
            userID = request.identity.userID,
            resource = supply.resource,
            amount = data.amount,
            inSortingCenter = false
          )

          if (supply.amount == data.amount)
            supplyService.deleteRowByID(supply.id)
          else {
            val updatedSupply = Supply(
              id = supply.id,
              userID = supply.userID,
              resource = supply.resource,
              amount = supply.amount - data.amount
            )

            supplyService.save(updatedSupply)
          }

          for {
            offer <- sortingCenterWarehouseService.save(offer.copy())
          } yield Redirect(routes.SortingCentersController.index())
        }
      }

    )
  }
}
