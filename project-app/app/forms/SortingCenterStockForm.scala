package forms

import play.api.data.Form
import play.api.data.Forms._

/**
 * The form which handles the submission of a offer to store in he sorting center stock.
 */
object SortingCenterStockForm {

  /**
   * A play framework form.
   */
  val form = Form(
    mapping(
      "supplyID" -> nonEmptyText,
      "amount" -> number(min = 1),
      "email" -> nonEmptyText,
      "resource" -> nonEmptyText,
      "resourceCategoryID" -> longNumber,
      "amountLabelID" -> longNumber

    )(Data.apply)(Data.unapply)
  )

  /**
   * form for the sorting center stock
   *
   * @param supplyID the identification of the supply
   * @param amount  the amount to save
   * @param email the email of the supplier
   * @param resource The name of the resource.
   * @param resourceCategoryID The id of the resource category.
   * @param amountLabelID The id of the resource amount label.
   */
  case class Data(supplyID: String, amount: Int, email: String, resource: String, resourceCategoryID: Long, amountLabelID: Long)

}
