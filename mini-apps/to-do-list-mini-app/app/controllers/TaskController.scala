package controllers

/**
 * Created by Lycantropus on 11-10-2015.
 */
import javax.inject.Inject

import dal.TaskRepository
import models.Task
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.{Future, ExecutionContext}


class TaskController  @Inject()(repo: TaskRepository, val messagesApi: MessagesApi)
                               (implicit ec: ExecutionContext) extends Controller with I18nSupport
{

  def index = Action
  {
    Ok(views.html.tasks.index(taskForm))
  }

  def show(id: Long) = Action.async { implicit request =>
    repo.lookup(id).map { task =>
      Ok(views.html.tasks.show(task))
      }
  }

  val taskForm: Form[CreateTaskForm] = Form {
    mapping(
    "description" -> text,
    "date" -> text
    )(CreateTaskForm.apply)(CreateTaskForm.unapply)
  }

  def addTask = Action.async
  {
    implicit request =>
    taskForm.bindFromRequest.fold (
    errorForm => {
      Future.successful(Ok(views.html.tasks.index(errorForm)))
    },
    task => {
      repo.create(task.description, task.date).map{ _ =>
        Redirect(routes.TaskController.index)

      }
    }
    )
  }

  def getTasks = Action.async{
    repo.all.map {tasks =>
    Ok(Json.toJson(tasks))}
  }

}
case class CreateTaskForm(description: String, date: String)
