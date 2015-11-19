package controllers

import java.util.UUID
import javax.inject.Inject

import com.mohiva.play.silhouette.api.{Environment, Silhouette}
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import forms.SortingCenterStockForm
import models.services.{SortingCenterStockService, SupplyService}
import models.{SortingCenterStock, Supply, User}
import play.api.i18n.MessagesApi

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 *
 * @param messagesApi
 * @param env
 * @param supplyService
 * @param sortingCenterStockService
 */
class SortingCentersController @Inject()(
                                          val messagesApi: MessagesApi,
                                          val env: Environment[User, CookieAuthenticator],
                                          supplyService: SupplyService,
                                          sortingCenterStockService: SortingCenterStockService)
  extends Silhouette[User, CookieAuthenticator] {

  def index = SecuredAction.async { implicit request =>
    supplyService.allExceptByUser(request.identity.userID).map { supplies =>
      Ok(views.html.sortingCenters.index(request.identity, SortingCenterStockForm.form, supplies))
    }
  }

  /**
   * Submits a resource supply and deletes the same entry in Supply table.
   *
   * @return The result to display.
   */
  def acceptOffer = SecuredAction.async { implicit request =>
    SortingCenterStockForm.form.bindFromRequest.fold(
      form => {
        supplyService.allExceptByUser(request.identity.userID).map { supplies =>
          BadRequest(views.html.sortingCenters.index(request.identity, form, supplies))
        }
      },
      data => {
        supplyService.retrieve(UUID.fromString(data.supplyID)).flatMap { supply =>
          // update supplies table
          if (supply.amount == data.amount) {
            supplyService.deleteRowByID(supply.id)
          } else {
            val updatedSupply = Supply(
              id = supply.id,
              userID = supply.userID,
              resource = supply.resource,
              amount = supply.amount - data.amount
            )

            supplyService.save(updatedSupply)
          }

          // update sorting center stocks table
          sortingCenterStockService.retrieve(supply.id, request.identity.userID).map {
            // updating if we already accepted part of this supply offer
            case Some(someStock) =>
              val stock = SortingCenterStock(
                id = someStock.id,
                idSupply = someStock.idSupply,
                userID = someStock.userID,
                resource = someStock.resource,
                amount = someStock.amount + data.amount
              )

              sortingCenterStockService.save(stock.copy())

            // adding a new supply offer we are accepting for the first time
            case None =>
              val stock = SortingCenterStock(
                id = UUID.randomUUID(),
                idSupply = supply.id,
                userID = request.identity.userID,
                resource = supply.resource,
                amount = data.amount
              )

              sortingCenterStockService.save(stock.copy())
          }

          Future.successful(Redirect(routes.SortingCentersController.index()))
        }
      }
    )
  }

}
