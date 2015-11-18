package modules

import com.google.inject.AbstractModule
import models.daos.{SortingCenterWarehouseDAOImpl, SortingCenterWarehouseDAO, SupplyDAO, SupplyDAOImpl}
import models.services.{SortingCenterWarehouseServiceImpl, SortingCenterWarehouseService, SupplyServiceImpl, SupplyService}
import net.codingwell.scalaguice.ScalaModule

/**
 * The Guice module which wires all the app dependencies.
 */
class AppModule extends AbstractModule with ScalaModule {

  /**
   * Configures the module.
   */
  def configure() {
    bind[SortingCenterWarehouseService].to[SortingCenterWarehouseServiceImpl]
    bind[SortingCenterWarehouseDAO].to[SortingCenterWarehouseDAOImpl]

    bind[SupplyService].to[SupplyServiceImpl]
    bind[SupplyDAO].to[SupplyDAOImpl]
  }

}
