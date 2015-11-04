package controllers

import play.api.mvc._
import play.api.i18n._
import play.api.data.Form
import play.api.data.Forms._

import dal.UserRepository

import utils.Utils

import scala.concurrent.{ ExecutionContext, Future }

import javax.inject._

class UserController @Inject()(repo: UserRepository, val messagesApi: MessagesApi)
                              (implicit ec: ExecutionContext) extends Controller with I18nSupport {

  def index = Action {
    Ok(views.html.users.register(registerForm))
  }

  val registerForm: Form[CreateRegisterForm] = Form {
    mapping(
      "username" -> nonEmptyText.verifying(),
      "password" -> nonEmptyText,
      "confirmPassword" -> nonEmptyText
    )(CreateRegisterForm.apply)(CreateRegisterForm.unapply)
  }

  private def validateForm(registerForm: Form[CreateRegisterForm]) = {
    if(registerForm("username").value.get == "" || registerForm("username").value.get == null ||
      registerForm("password").value.get == "" || registerForm("password").value.get == null ||
      registerForm("password").value.get.length < 6) {
      registerForm.withError("username", "Required field")
      registerForm

    }
    else
      registerForm
  }

  def addUser = Action.async { implicit request =>
    registerForm.bindFromRequest.fold(
      errorForm => {
        Future.successful(Ok(views.html.users.register(validateForm(errorForm))))
      },
      user => {
        repo.create(user.username, user.password).map { _ =>
          Redirect(routes.AppController.index)
        }
      }
    )
  }
}

case class CreateRegisterForm(username: String, password: String, confirmPassword: String)
