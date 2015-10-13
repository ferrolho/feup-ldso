package controllers

/**
 * Created by Lycantropus on 12-10-2015.
 */
import javax.inject.Inject

import dal.UserRepository
import models.User
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.{Future, ExecutionContext}

class UserController @Inject()(repo: UserRepository, val messagesApi: MessagesApi)
                              (implicit ec: ExecutionContext) extends Controller
{

  val userForm: Form[CreateUserForm] = Form {
    mapping(
      "name" -> text
    )(CreateUserForm.apply)(CreateUserForm.unapply)
  }

  def index = Action {
    Ok(views.html.users.index(userForm))
  }



}

case class CreateUserForm(name: String)
