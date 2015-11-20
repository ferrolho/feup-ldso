package models.daos

import java.util.UUID

import models.SortingCenterStock

import scala.concurrent.Future

trait SortingCenterStockDAO {

  /**
   * Finds a SortingCenterStock by its ID.
   *
   * @param idSupply The ID of the supply to find.
   * @param userID The ID of the session user to find.
   * @return The found supply or None if no supply for the given ID could be found.
   */
  def find(idSupply: UUID, userID: UUID): Future[Option[SortingCenterStock]]

  /**
   * Retrieves all sorting center stock of a certain user from the DB.
   *
   * @param userID The id of the user.
   * @return The sequence of sorting center stock.
   */
  def byUser(userID: UUID): Future[Seq[SortingCenterStock]]

  /**
   * Saves a SortingCenter.
   *
   * @param sortingCenterStock The sorting center to save.
   * @return The saved sorting center.
   */
  def save(sortingCenterStock: SortingCenterStock): Future[SortingCenterStock]

}
