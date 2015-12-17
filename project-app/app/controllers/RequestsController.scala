package controllers

import java.util.UUID
import javax.inject.Inject

import com.mohiva.play.silhouette.api.{Environment, Silhouette}
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import models.services.{ResourceAmountLabelService, ResourceCategoryService, SortingCenterStockService}
import models.User
import play.api.i18n.MessagesApi

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * The requests controller
 *
 * @param messagesApi
 * @param env
 * @param sortingCenterStockService
 * @param resourceCategoryService
 * @param resourceAmountLabelService
 */
class RequestsController @Inject()(
                                    val messagesApi: MessagesApi,
                                    val env: Environment[User, CookieAuthenticator],
                                    sortingCenterStockService: SortingCenterStockService,
                                    resourceCategoryService: ResourceCategoryService,
                                    resourceAmountLabelService: ResourceAmountLabelService)
  extends Silhouette[User, CookieAuthenticator] {

  def index = SecuredAction.async { implicit request =>
    val fAllSortingCenterStockUser = sortingCenterStockService.allExceptRelatedToUser(request.identity.userID)
    val fResourceCategories = resourceCategoryService.all
    val fResourceAmountLabels = resourceAmountLabelService.all

    for {
      allSortingCenterStocks <- fAllSortingCenterStockUser
      resourceCategories <- fResourceCategories
      resourceAmountLabels <- fResourceAmountLabels
    } yield {
      val sortingCenterStocks = allSortingCenterStocks
      val resourceCategoryOptions = resourceCategories.map { model => (model.id.toString, model.name) }
      val resourceAmountLabelOptions = resourceAmountLabels.map { model => (model.id.toString, model.name) }

      Ok(views.html.requests.index(request.identity, sortingCenterStocks, resourceCategoryOptions, resourceAmountLabelOptions))
    }
  }

  /**
   * Submits a request.
   *
   * @return The result to display.
   */
  def submitSupplyOffer = SecuredAction.async { implicit request =>
    RequestForm.form.bindFromRequest.fold(
      form => {
        val fSupplies = supplyService.byUser(request.identity.userID)
        val fResourceCategories = resourceCategoryService.all
        val fResourceAmountLabels = resourceAmountLabelService.all

        for {supplies <- fSupplies; resourceCategories <- fResourceCategories; resourceAmountLabels <- fResourceAmountLabels}
          yield {
            val resourceCategorySelectOptions = resourceCategories.map { model => (model.id.toString, model.name) }
            val resourceAmountLabelSelectOptions = resourceAmountLabels.map { model => (model.id.toString, model.name) }

            BadRequest(views.html.supplies.index(request.identity, form, supplies, resourceCategorySelectOptions, resourceAmountLabelSelectOptions))
          }
      },
      data => {
        val supply = Request(
          id = UUID.randomUUID(),
          userID = request.identity.userID,
          resource = data.resource,
          resourceCategoryID = data.resourceCategoryID,
          amount = data.amount,
          amountLabelID = data.amountLabelID
        )

        for {
          supply <- supplyService.save(supply.copy())
        } yield Redirect(routes.SuppliesController.index())
      }
    )
  }

}
