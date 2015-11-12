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
      "amount" -> number(min = 0)
    )(Data.apply)(Data.unapply)
  )

  /**
   * The form data.
   *
   * @param resource The name of the resource.
   * @param amount The amount of resource units being supplied.
   */
  case class Data(resource: String, amount: Int)

}
