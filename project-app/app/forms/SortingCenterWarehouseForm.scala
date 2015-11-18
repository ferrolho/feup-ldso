package forms

import play.api.data.Form
import play.api.data.Forms._

/**
 * The form which handles the submission of a offer to store in he sorting center warehouse.
 */
object SortingCenterWarehouseForm {

  /**
   * A play framework form.
   */
  val form = Form(
    mapping(
      "supplyID" -> nonEmptyText,
      "amount" -> number(min = 1)
    )(Data.apply)(Data.unapply)
  )

  /**
   * form for the sorting center warehouse
   *
   * @param supplyID the identification of the supply
   * @param amount  the amount to save
   */
  case class Data(supplyID: String, amount: Int)

}
