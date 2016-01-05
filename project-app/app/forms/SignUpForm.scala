package forms

import play.api.data.Form
import play.api.data.Forms._

/**
 * The form which handles the sign up process.
 */
object SignUpForm {

  /**
   * A play framework form.
   */
  val form = Form(
    mapping(
      "firstName" -> nonEmptyText,
      "lastName" -> nonEmptyText,
      "countryID" -> longNumber,
      "city" -> nonEmptyText,
      "email" -> email,
      "password" -> nonEmptyText
    )(Data.apply)(Data.unapply)
  )

  /**
   * The form data.
   *
   * @param firstName The first name of a user.
   * @param lastName The last name of a user.
   * @param countryID The id of the user's country.
   * @param email The email of the user.
   * @param password The password of the user.
   */
  case class Data(
                   firstName: String,
                   lastName: String,
                   countryID: Long,
                   city: String,
                   email: String,
                   password: String)

}
