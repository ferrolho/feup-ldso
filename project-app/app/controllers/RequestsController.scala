package controllers

import javax.inject.Inject

import com.mohiva.play.silhouette.api.{Environment, Silhouette}
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import models.services.{ResourceAmountLabelService, ResourceCategoryService, SortingCenterStockService, SupplyService}
import models.User
import play.api.i18n.MessagesApi

import scala.concurrent.ExecutionContext.Implicits.global

/**
 *
 * @param messagesApi
 * @param env
 * @param sortingCenterStockService
 */
class RequestsController @Inject()(
                                    val messagesApi: MessagesApi,
                                    val env: Environment[User, CookieAuthenticator],
                                    sortingCenterStockService: SortingCenterStockService,
                                    resourceCategoryService: ResourceCategoryService,
                                    resourceAmountLabelService: ResourceAmountLabelService)
  extends Silhouette[User, CookieAuthenticator] {

  def index = SecuredAction.async { implicit request =>
    val fAllSortingCenterStockUser = sortingCenterStockService.byUser(request.identity.userID)
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

}
