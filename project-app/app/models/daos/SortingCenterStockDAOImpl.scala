package models.daos

import javax.inject.Inject

import models.SortingCenterStock
import play.api.db.slick.DatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext

/**
 * Give access to the sorting center stock object using Slick
 */
class SortingCenterStockDAOImpl @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends SortingCenterStockDAO with DAOSlick {

  import driver.api._

  /**
   * Saves a sorting center.
   *
   * @param sortingCenterStock The sorting center to save.
   * @return The saved sorting center.
   */
  def save(sortingCenterStock: SortingCenterStock) = {
    val dbSortingCenterStock = DBSortingCenterStock(
      sortingCenterStock.idResource.toString,
      sortingCenterStock.idSortingCenter.toString,
      sortingCenterStock.userID.toString,
      sortingCenterStock.resource.toString,
      sortingCenterStock.amount,
      sortingCenterStock.inSortingCenter
    )

    //combine database actions to be run sequentially
    val actions = slickSortingCenterStock.insertOrUpdate(dbSortingCenterStock).transactionally

    //run actions and return sortingCenter afterwards
    db.run(actions).map(_ => sortingCenterStock)
  }

}
