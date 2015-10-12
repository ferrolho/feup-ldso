package controllers

import play.api.mvc._
import play.api.i18n._
import play.api.data.Form
import play.api.data.Forms._
import dal._

import scala.concurrent.{ ExecutionContext, Future }

import javax.inject._

class PostController @Inject()(repo: PostRepository, val messagesApi: MessagesApi)
                              (implicit ec: ExecutionContext) extends Controller with I18nSupport {

  def index = Action {
    Ok(views.html.posts.addPost(postForm))
  }

  /**
   * The mapping for the post form.
   */
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

/**
 * The create post form.
 *
 * Generally for forms, you should define separate objects to your models, since forms very often need to present data
 * in a different way to your models.  In this case, it doesn't make sense to have an id parameter in the form, since
 * that is generated once it's created.
 */
case class CreatePostForm(subject: String, description: String)