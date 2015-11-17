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
      "resource" -> nonEmptyText,
      "amount" -> number(min = 1)
    )(Data.apply)(Data.unapply)
  )

  /**
   * The form data.
   *
   * @param resource The name of the resource.
   * @param amount The amount of resource units being stored.
   */
  case class Data(resource: String, amount: Int)


}
