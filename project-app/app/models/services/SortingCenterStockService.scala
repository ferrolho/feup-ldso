package models.services

import java.util.UUID

import models.SortingCenterStock

import scala.concurrent.Future

/**
 * Handles actions to sorting center stock.
 */
trait SortingCenterStockService {

  /**
   * Retrieve a sorting center stock by the ID of one supply and the ID of the user in session.
   *
   * @param idSupply The id of the supply we want.
   * @param userID The id of the user in session.
   * @return The sorting center stock.
   */
  def retrieve(idSupply: UUID, userID: UUID): Future[Option[SortingCenterStock]]

  /**
   * Retrieves all sorting center stock of a certain user.
   *
   * @param userID The id of the user.
   * @return The sequence of sorting center stock.
   */
  def byUser(userID: UUID) :Future[Seq[SortingCenterStock]]

  /**
   * Saves an acceptable offer to store in sorting center stock.
   *
   * @param sortingCenterStock the acceptable offer to save.
   * @return The saved acceptable offer.
   */
  def save(sortingCenterStock: SortingCenterStock): Future[SortingCenterStock]

}
