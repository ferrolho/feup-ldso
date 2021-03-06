package controllers

import java.util.UUID
import javax.inject.Inject

import com.mohiva.play.silhouette.api.{Environment, Silhouette}
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import forms.SupplyForm
import models.services.{ResourceAmountLabelService, ResourceCategoryService, SupplyService}
import models.{Supply, User}
import play.api.i18n.MessagesApi

import scala.concurrent.ExecutionContext.Implicits.global

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
                                    supplyService: SupplyService,
                                    resourceCategoryService: ResourceCategoryService,
                                    resourceAmountLabelService: ResourceAmountLabelService)
  extends Silhouette[User, CookieAuthenticator] {

  def index = SecuredAction.async { implicit request =>
    val fSupplies = supplyService.byUser(request.identity.userID)
    val fResourceCategories = resourceCategoryService.all
    val fResourceAmountLabels = resourceAmountLabelService.all

    for {supplies <- fSupplies; resourceCategories <- fResourceCategories; resourceAmountLabels <- fResourceAmountLabels}
      yield {
        val resourceCategorySelectOptions = resourceCategories.map { model => (model.id.toString, model.name) }
        val resourceAmountLabelSelectOptions = resourceAmountLabels.map { model => (model.id.toString, model.name) }

        Ok(views.html.supplies.index(request.identity, SupplyForm.form, supplies, resourceCategorySelectOptions, resourceAmountLabelSelectOptions))
      }
  }

  /**
   * Submits a resource supply.
   *
   * @return The result to display.
   */
  def submitSupplyOffer = SecuredAction.async { implicit request =>
    SupplyForm.form.bindFromRequest.fold(
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
        val supply = Supply(
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

  def editSupplyOffer(supplyID: String) = SecuredAction.async { implicit request =>
    SupplyForm.form.bindFromRequest.fold(
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
        val supply = Supply(
          id = UUID.fromString(supplyID),
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

  def deleteSupplyOffer(supplyID: String) = SecuredAction.async { implicit request =>
    supplyService.deleteRowByID(UUID.fromString(supplyID))

    val fSupplies = supplyService.byUser(request.identity.userID)
    val fResourceCategories = resourceCategoryService.all
    val fResourceAmountLabels = resourceAmountLabelService.all

    for {supplies <- fSupplies; resourceCategories <- fResourceCategories; resourceAmountLabels <- fResourceAmountLabels}
      yield {
        val resourceCategorySelectOptions = resourceCategories.map { model => (model.id.toString, model.name) }
        val resourceAmountLabelSelectOptions = resourceAmountLabels.map { model => (model.id.toString, model.name) }

        Ok(views.html.supplies.index(request.identity, SupplyForm.form, supplies, resourceCategorySelectOptions, resourceAmountLabelSelectOptions))
      }
  }

}
