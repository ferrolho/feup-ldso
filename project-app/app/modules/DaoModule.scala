package modules

import com.google.inject.AbstractModule
import models.daos.{SupplyDAO, SupplyDAOImpl}
import net.codingwell.scalaguice.ScalaModule

/**
 * The Guice module which wires all Silhouette dependencies.
 */
class DaoModule extends AbstractModule with ScalaModule {

  /**
   * Configures the module.
   */
  def configure() {
    bind[SupplyDAO].to[SupplyDAOImpl]
  }

}
