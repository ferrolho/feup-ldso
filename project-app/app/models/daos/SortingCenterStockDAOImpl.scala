package models.daos

import java.util.UUID
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
   * Finds a sortingCenterStock by its ID.
   *
   * @param idSupply The ID of the supply to find.
   * @param userID The ID of the user session to find.
   * @return The found sortingCenterStock or None if no sortingCenterStock for the given IDs could be found.
   */
  def find(idSupply: UUID, userID: UUID) = {
    val query = for {
      dbSortingCenterStock <- slickSortingCenterStocks.filter(x => x.idSupply === idSupply.toString && x.userID === userID.toString)
    } yield dbSortingCenterStock

    db.run(query.result.headOption).map { resultOption =>
      resultOption.map { stock =>
        SortingCenterStock(
          UUID.fromString(stock.id),
          UUID.fromString(stock.idSupply),
          UUID.fromString(stock.userID),
          UUID.fromString(stock.idSortingCenter),
          stock.resource,
          stock.amount,
          stock.inSortingCenter)
      }
    }
  }

  /**
   * Saves a sorting center.
   *
   * @param sortingCenterStock The sorting center to save.
   * @return The saved sorting center.
   */
  def save(sortingCenterStock: SortingCenterStock) = {
    val dbSortingCenterStock = DBSortingCenterStock(
      sortingCenterStock.id.toString,
      sortingCenterStock.idSupply.toString,
      sortingCenterStock.idSortingCenter.toString,
      sortingCenterStock.userID.toString,
      sortingCenterStock.resource.toString,
      sortingCenterStock.amount,
      sortingCenterStock.inSortingCenter
    )

    //combine database actions to be run sequentially
    val actions = slickSortingCenterStocks.insertOrUpdate(dbSortingCenterStock).transactionally

    //run actions and return sortingCenter afterwards
    db.run(actions).map(_ => sortingCenterStock)
  }

}
