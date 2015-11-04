package controllers

import play.api.mvc._
import play.api.i18n._
import play.api.data.Form
import play.api.data.Forms._
import dal.PostRepository

import scala.concurrent.{ ExecutionContext, Future }

import javax.inject._

class PostController @Inject()(repo: PostRepository, val messagesApi: MessagesApi)
                              (implicit ec: ExecutionContext) extends Controller with I18nSupport {

  def index = Action {
    Ok(views.html.posts.addPost(postForm))
  }

  val postForm: Form[CreatePostForm] = Form {
    mapping(
      "subject" -> nonEmptyText,
      "description" -> nonEmptyText
    )(CreatePostForm.apply)(CreatePostForm.unapply)
  }

  def addPost = Action.async { implicit request =>
    postForm.bindFromRequest.fold(
      errorForm => {
        Future.successful(Ok(views.html.posts.addPost(postForm)))
      },
      post => {
        repo.create(post.subject, post.description).map { _ =>
          Redirect(routes.AppController.index)
        }
      }
    )
  }

  def getPosts = Action.async {
    repo.list().map {
      posts => Ok(views.html.posts.posts(posts))
    }
  }
}

case class CreatePostForm(subject: String, description: String)