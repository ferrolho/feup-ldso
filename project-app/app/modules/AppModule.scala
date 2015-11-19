package modules

import com.google.inject.AbstractModule
import models.daos._
import models.services._
import net.codingwell.scalaguice.ScalaModule

/**
 * The Guice module which wires all the app dependencies.
 */
class AppModule extends AbstractModule with ScalaModule {

  /**
   * Configures the module.
   */
  def configure() {
    bind[SortingCenterStockService].to[SortingCenterStockServiceImpl]
    bind[SortingCenterStockDAO].to[SortingCenterStockDAOImpl]

    bind[SupplyService].to[SupplyServiceImpl]
    bind[SupplyDAO].to[SupplyDAOImpl]

    bind[ResourceCategoryService].to[ResourceCategoryServiceImpl]
    bind[ResourceCategoryDAO].to[ResourceCategoryDAOImpl]

    bind[ResourceAmountLabelService].to[ResourceAmountLabelServiceImpl]
    bind[ResourceAmountLabelDAO].to[ResourceAmountLabelDAOImpl]
  }

}
