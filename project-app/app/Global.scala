import play.api._

package object Global extends GlobalSettings {

  lazy val appName = "Food Chains"

  override def onStart(app: Application) {
    Logger.info("Application has started")
  }

  override def onStop(app: Application) {
    Logger.info("Application shutdown...")
  }

}
