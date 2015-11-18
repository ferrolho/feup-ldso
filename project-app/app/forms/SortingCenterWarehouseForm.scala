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
      "supplyID" -> nonEmptyText
    )(Data.apply)(Data.unapply)
  )

  case class Data(supplyID: String)

}
