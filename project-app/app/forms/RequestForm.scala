package forms

import play.api.data.Form
import play.api.data.Forms._

/**
 * The form which handles the submission of a request.
 */
object RequestForm {

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
   * The form data.
   *
   * @param supplyID The name of the resource.
   * @param amount The amount of resource units being supplied.
   */
  case class Data(supplyID: String, amount: Int)

}
