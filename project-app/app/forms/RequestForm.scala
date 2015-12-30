package forms

import play.api.data.Form
import play.api.data.Forms._

object RequestForm {

  val form = Form(
    mapping(
      "supplyID" -> nonEmptyText,
      "amount" -> number(min = 1)
    )(Data.apply)(Data.unapply)
  )

  case class Data(supplyID: String, amount: Int)

}
