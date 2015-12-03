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
          UUID.fromString(stock.supplyUserID),
          stock.resource,
          stock.resourceCategoryID,
          stock.amount,
          stock.amountLabelID
        )
      }
    }
  }

  /**
   * Finds a SortingCenterStock by its ID.
   *
   * @param id The ID of the stock to find.
   * @return The found stock or None if no stock for the given ID could be found.
   */
  def find(id: UUID) = {
    val query = for {
      dbSortingCenterStock <- slickSortingCenterStocks.filter(_.id === id.toString)
    } yield dbSortingCenterStock

    db.run(query.result.head).map { stock =>
      SortingCenterStock(
        UUID.fromString(stock.id),
        UUID.fromString(stock.idSupply),
        UUID.fromString(stock.userID),
        UUID.fromString(stock.supplyUserID),
        stock.resource,
        stock.resourceCategoryID,
        stock.amount,
        stock.amountLabelID
      )
    }
  }

  /**
   * Retrieves all sorting center stock of a certain user from the DB.
   *
   * @param userID The id of the user.
   * @return The sequence of sorting center stock.
   */
  def byUser(userID: UUID) = {
    val sortingCenterStocksQuery = for {
      dbSortingCenterStock <- slickSortingCenterStocks.filter(_.userID === userID.toString)
    } yield dbSortingCenterStock

    db.run(sortingCenterStocksQuery.result).map { dbSortingCenterStockOption =>
      dbSortingCenterStockOption.map { stock =>
        SortingCenterStock(
          UUID.fromString(stock.id),
          UUID.fromString(stock.idSupply),
          UUID.fromString(stock.userID),
          UUID.fromString(stock.supplyUserID),
          stock.resource,
          stock.resourceCategoryID,
          stock.amount,
          stock.amountLabelID
        )
      }
    }
  }


  /**
   * Retrieves all sorting center stock of a certain user from the DB.
   *
   * @param userID The id of the user.
   * @return The sequence of sorting center stock.
   */
  def allExceptByUser(idSupply: UUID, userID: UUID) = {
    val sortingCenterStocksQuery = for {
      dbSortingCenterStock <- slickSortingCenterStocks.filter(x => x.idSupply === idSupply.toString && x.userID === userID.toString)
    } yield dbSortingCenterStock

    db.run(sortingCenterStocksQuery.result).map { dbSortingCenterStockOption =>
      dbSortingCenterStockOption.map { stock =>
        SortingCenterStock(
          UUID.fromString(stock.id),
          UUID.fromString(stock.idSupply),
          UUID.fromString(stock.userID),
          UUID.fromString(stock.supplyUserID),
          stock.resource,
          stock.resourceCategoryID,
          stock.amount,
          stock.amountLabelID
        )
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
      sortingCenterStock.userID.toString,
      sortingCenterStock.supplyUserID.toString,
      sortingCenterStock.resource.toString,
      sortingCenterStock.resourceCategoryID,
      sortingCenterStock.amount,
      sortingCenterStock.amountLabelID
    )

    //combine database actions to be run sequentially
    val actions = slickSortingCenterStocks.insertOrUpdate(dbSortingCenterStock).transactionally

    //run actions and return sortingCenter afterwards
    db.run(actions).map(_ => sortingCenterStock)
  }

  /**
  * deletes the row with the param id
  *
  * @param id the id of the sorting center stock to remove.
    *
  */
  def delete(id: UUID) {
    db.run(slickSortingCenterStocks.filter(_.id === id.toString).delete)
  }
}
