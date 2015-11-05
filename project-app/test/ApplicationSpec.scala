import org.junit.runner._
import org.specs2.mutable._
import org.specs2.runner._
import play.api.test.Helpers._
import play.api.test._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends Specification {

  "Application" should {

    //First test, to understand sintax
    "contain 11 characters" in {
      "Hello world" must have size (11)
    }

    "send 404 on a bad request" in new WithApplication {
      route(FakeRequest(GET, "/boum")) must beSome.which(status(_) == NOT_FOUND)
    }

    "render the sign-in page" in new WithApplication {
      val home = route(FakeRequest(GET, "/sign-in")).get

      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain("Sign in with your credentials")
    }

    "render the sign-up page" in new WithApplication() {
      val home = route(FakeRequest(GET, "/sign-up")).get

      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain("Sign up for a new account")
    }

    "verify sign-up form sent correctly" in new WithApplication() {
      val home = route(FakeRequest(GET, "/sign-up")).get
    }

  }

}
