package models.daos

import javax.inject.Inject

import models.SortingCenterWarehouse
import play.api.db.slick.DatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext

/**
 * Give access to the sorting center warehouse object using Slick
 */
class SortingCenterWarehouseDAOImpl @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends SortingCenterWarehouseDAO with DAOSlick {

  import driver.api._

  /**
   * Saves a sorting center.
   *
   * @param sortingCenterWarehouse The sorting center to save.
   * @return The saved sorting center.
   */
  def save(sortingCenterWarehouse: SortingCenterWarehouse) = {
    val dbSortingCenterWarehouse = DBSortingCenterWarehouse(
      sortingCenterWarehouse.idResource.toString,
      sortingCenterWarehouse.idSortingCenter.toString,
      sortingCenterWarehouse.userID.toString,
      sortingCenterWarehouse.resource.toString,
      sortingCenterWarehouse.amount,
      sortingCenterWarehouse.inSortingCenter
    )

    //combine database actions to be run sequentially
    val actions = slickSortingCenterWarehouse.insertOrUpdate(dbSortingCenterWarehouse).transactionally

    //run actions and return sortingCenter afterwards
    db.run(actions).map(_ => sortingCenterWarehouse)
  }

}
