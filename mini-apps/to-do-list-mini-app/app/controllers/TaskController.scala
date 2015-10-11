package controllers

/**
 * Created by Lycantropus on 11-10-2015.
 */

import javax.inject.Inject

import dal.TaskRepository
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.{MessagesApi, I18nSupport}
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

}
