package controllers

import play.api.mvc._

class Application extends Controller {

  def index = Action {
    Ok(views.html.index())
  }

  def dashboard = Action {
    Ok(views.html.dashboard())
  }

  def playInfo = Action {
    Ok(views.html.playInfo("Your application is ready."))
  }

}
