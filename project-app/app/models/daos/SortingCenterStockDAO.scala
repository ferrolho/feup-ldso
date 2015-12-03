package models.daos

import java.util.UUID

import models.SortingCenterStock

import scala.concurrent.Future

trait SortingCenterStockDAO {

  /**
   * Finds a SortingCenterStock by the ID of the supply it has and by the ID of the stock owner user.
   *
   * @param idSupply The ID of the supply to find.
   * @param userID The ID of the session user to find.
   * @return The found stock or None if no stock for the given IDs could be found.
   */
  def find(idSupply: UUID, userID: UUID): Future[Option[SortingCenterStock]]

  /**
   * Finds a SortingCenterStock by its ID.
   *
   * @param id The ID of the stock to find.
   * @return The found stock or None if no stock for the given ID could be found.
   */
  def find(id: UUID): Future[SortingCenterStock]

  /**
   * Retrieves all sorting center stock of a certain user from the DB.
   *
   * @param userID The id of the user.
   * @return The sequence of sorting center stock.
   */
  def byUser(userID: UUID): Future[Seq[SortingCenterStock]]

  /**
   * Retrieves all sorting center stock of a certain user from the DB.
   *
   * @param userID The id of the user.
   * @return The sequence of sorting center stock.
   */
  def allExceptByUser(userID: UUID): Future[Seq[SortingCenterStock]]
  /**
   * Saves a SortingCenter.
   *
   * @param sortingCenterStock The sorting center to save.
   * @return The saved sorting center.
   */
  def save(sortingCenterStock: SortingCenterStock): Future[SortingCenterStock]

  /**
   * deletes the row with the param id
   *
   * @param id the id of the sorting center stock to remove.
   *
   */
  def delete(id: UUID)
}
