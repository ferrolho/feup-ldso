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
    Ok(views.html.post("puta"))
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

  /*def addPost = Action {
    Ok(views.html.post(postForm))
  }*/

  /*def addPost = Action.async { implicit request =>
    // Bind the form first, then fold the result, passing a function to handle errors, and a function to handle succes.
    postForm.bindFromRequest.fold(
      // The error function. We return the index page with the error form, which will render the errors.
      // We also wrap the result in a successful future, since this action is synchronous, but we're required to return
      // a future because the person creation function returns a future.
      errorForm => {
        Future.successful(Ok(views.html.index()))
      },
      // There were no errors in the from, so create the person.
      post => {
        repo.create(post.subject, post.description).map { _ =>
          // If successful, we simply redirect to the index page.
          Redirect(routes.PostController.index)
        }
      }
    )
  }*/
}

/**
 * The create post form.
 *
 * Generally for forms, you should define separate objects to your models, since forms very often need to present data
 * in a different way to your models.  In this case, it doesn't make sense to have an id parameter in the form, since
 * that is generated once it's created.
 */
case class CreatePostForm(subject: String, description: String)