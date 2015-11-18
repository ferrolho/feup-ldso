package models.services

import java.util.UUID

import models.Supply

import scala.concurrent.Future

/**
 * Handles actions to supplies.
 */
trait SupplyService {

  def retrieve(id: UUID): Future[Supply]

  /**
   * Retrieves all supplies submitted by a certain user.
   *
   * @param userID The id of the user to retrieve the supplies.
   * @return The sequence of supplies.
   */
  def byUser(userID: UUID): Future[Seq[Supply]]

  def allExceptByUser(userID: UUID): Future[Seq[Supply]]

  /**
   * Retrieves all supplies from the DB.
   *
   * @return The sequence of supplies.
   */
  def all: Future[Seq[Supply]]

  /**
   * Saves a supply offer.
   *
   * @param user The user to save.
   * @return The saved user.
   */
  def save(user: Supply): Future[Supply]

  /**
   * deletes the row with the param id ( to remove the offer of a supplier
   *
   * @param id the id of the supply to remove.
   * @return .
   */

  def deleteRowByID(id:UUID): Future[Int]

}
