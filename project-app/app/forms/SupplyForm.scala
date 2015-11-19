package forms

import play.api.data.Form
import play.api.data.Forms._

/**
 * The form which handles the submission of a supply offer.
 */
object SupplyForm {

  /**
   * A play framework form.
   */
  val form = Form(
    mapping(
      "resource" -> nonEmptyText,
      "resourceCategoryID" -> longNumber,
      "amount" -> number(min = 1)
    )(Data.apply)(Data.unapply)
  )

  /**
   * The form data.
   *
   * @param resource The name of the resource.
   * @param resourceCategoryID The id of the resource category.
   * @param amount The amount of resource units being supplied.
   */
  case class Data(resource: String, resourceCategoryID: Long, amount: Int)

}
