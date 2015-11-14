package modules

import com.google.inject.AbstractModule
import models.daos.{SupplyDAO, SupplyDAOImpl}
import models.services.{SupplyServiceImpl, SupplyService}
import net.codingwell.scalaguice.ScalaModule

/**
 * The Guice module which wires all the app dependencies.
 */
class AppModule extends AbstractModule with ScalaModule {

  /**
   * Configures the module.
   */
  def configure() {
    bind[SupplyService].to[SupplyServiceImpl]
    bind[SupplyDAO].to[SupplyDAOImpl]
  }

}
