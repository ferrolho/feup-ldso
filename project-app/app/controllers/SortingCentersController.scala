package controllers

import java.util.UUID
import javax.inject.Inject

import com.mohiva.play.silhouette.api.{Environment, Silhouette}
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import forms.SortingCenterStockForm
import models.services.{ResourceAmountLabelService, ResourceCategoryService, SortingCenterStockService, SupplyService}
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
                                          sortingCenterStockService: SortingCenterStockService,
                                          resourceCategoryService: ResourceCategoryService,
                                          resourceAmountLabelService: ResourceAmountLabelService)
  extends Silhouette[User, CookieAuthenticator] {

  def index = SecuredAction.async { implicit request =>
    val fAllSupliesExceptUser = supplyService.allExceptByUser(request.identity.userID)
    val fResourceCategories = resourceCategoryService.all
    val fResourceAmountLabels = resourceAmountLabelService.all

    for {
      allSuppliesExceptUser <- fAllSupliesExceptUser; resourceCategories <- fResourceCategories; resourceAmountLabels <- fResourceAmountLabels
    }
      yield {
        val supplies = allSuppliesExceptUser
        val resourceCategoryOptions = resourceCategories.map { model => (model.id.toString, model.name) }
        val resourceAmountLabelOptions = resourceAmountLabels.map { model => (model.id.toString, model.name) }

        Ok(views.html.sortingCenters.index(request.identity, SortingCenterStockForm.form, supplies, resourceCategoryOptions, resourceAmountLabelOptions))
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
        val fAllSuppliesExceptUser = supplyService.allExceptByUser(request.identity.userID)
        val fResourceCategories = resourceCategoryService.all
        val fResourceAmountLabels = resourceAmountLabelService.all

        for {allSuppliesExceptUser <- fAllSuppliesExceptUser; resourceCategories <- fResourceCategories; resourceAmountLabels <- fResourceAmountLabels}
          yield {
            val supplies = allSuppliesExceptUser
            val resourceCategoryOptions = resourceCategories.map { model => (model.id.toString, model.name) }
            val resourceAmountLabelOptions = resourceAmountLabels.map { model => (model.id.toString, model.name) }

            BadRequest(views.html.sortingCenters.index(request.identity, form, supplies, resourceCategoryOptions, resourceAmountLabelOptions))
          }
      },
      data => {
        supplyService.retrieve(UUID.fromString(data.supplyID)).flatMap {
          case Some(supply) =>
            // update supplies table
            if (supply.amount == data.amount) {
              supplyService.deleteRowByID(supply.id)
            } else {
              val updatedSupply = Supply(
                id = supply.id,
                userID = supply.userID,
                resource = supply.resource,
                resourceCategoryID = supply.resourceCategoryID,
                amount = supply.amount - data.amount,
                amountLabelID = supply.amountLabelID
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
                  supplyUserID = someStock.supplyUserID,
                  resource = someStock.resource,
                  resourceCategoryID = someStock.resourceCategoryID,
                  amount = someStock.amount + data.amount,
                  amountLabelID = someStock.amountLabelID
                )

                sortingCenterStockService.save(stock.copy())

              // adding a new supply offer we are accepting for the first time
              case None =>
                val stock = SortingCenterStock(
                  id = UUID.randomUUID(),
                  idSupply = supply.id,
                  userID = request.identity.userID,
                  supplyUserID = supply.userID,
                  resource = supply.resource,
                  resourceCategoryID = supply.resourceCategoryID,
                  amount = data.amount,
                  amountLabelID = supply.amountLabelID
                )

                sortingCenterStockService.save(stock.copy())
            }

            Future.successful(Redirect(routes.SortingCentersController.incomingResources))
        }
      }
    )
  }

  /**
   * Gets all session's user sorting center stocks
   *
   * @return The result to display
   */
  def incomingResources = SecuredAction.async { implicit request =>

    val fAllSortingCenterStockUser = sortingCenterStockService.byUser(request.identity.userID)
    val fResourceCategories = resourceCategoryService.all
    val fResourceAmountLabels = resourceAmountLabelService.all

    for {
      allSortingCenterStocks <- fAllSortingCenterStockUser
      resourceCategories <- fResourceCategories;
      resourceAmountLabels <- fResourceAmountLabels
    }
      yield {
        val sortingCenterStocks = allSortingCenterStocks
        val resourceCategoryOptions = resourceCategories.map { model => (model.id.toString, model.name) }
        val resourceAmountLabelOptions = resourceAmountLabels.map { model => (model.id.toString, model.name) }

        Ok(views.html.sortingCenters.incomingResources(request.identity, sortingCenterStocks, resourceCategoryOptions, resourceAmountLabelOptions))
      }
  }

  /**
   * Cancels a previous sorting center stock request and updates the avaiable supllies
   *
   * @return The result to display
   */
  def cancelIncomingResource(sortingCenterStockID: String) = SecuredAction.async { implicit request =>
    sortingCenterStockService.retrieve(UUID.fromString(sortingCenterStockID)).flatMap { stock =>
      supplyService.retrieve(stock.idSupply).map {
        case Some(supply) =>
          supplyService.save(Supply(
            supply.id,
            supply.userID,
            supply.resource,
            supply.resourceCategoryID,
            amount = supply.amount + stock.amount,
            supply.amountLabelID
          ))

          sortingCenterStockService.delete(stock.id)

        case None =>
          supplyService.save(Supply(
            stock.idSupply,
            stock.supplyUserID,
            stock.resource,
            stock.resourceCategoryID,
            stock.amount,
            stock.amountLabelID
          ))

          sortingCenterStockService.delete(stock.id)
      }

      Future.successful(Redirect(routes.SortingCentersController.incomingResources))
    }
  }
}
