package controllers

import play.api.mvc._

class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Hello."))
  }

  def test(name: String) = Action {
    Ok("Hello " + name)
  }

  def todo = TODO

}

