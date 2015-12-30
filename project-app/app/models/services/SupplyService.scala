package models.services

import java.util.UUID

import models.Supply

import scala.concurrent.Future

/**
 * Handles actions to supplies.
 */
trait SupplyService {

  /**
   * Retrieve a supply by its ID
   *
   * @param id The id of the retrieved supply
   * @return The supply
   */
  def retrieve(id: UUID): Future[Option[Supply]]

  /**
   * Retrieves all supplies submitted by a certain user.
   *
   * @param userID The id of the user to retrieve the supplies.
   * @return The sequence of supplies.
   */
  def byUser(userID: UUID): Future[Seq[Supply]]

  /**
   * Retrieves all supplies excepts the ones of a certain user.
   *
   * @param userID The id of the user to to be excluded.
   * @return The sequence of supplies.
   */
  def allExceptByUser(userID: UUID): Future[Seq[Supply]]

  /**
   * Retrieves all supplies by category excepts the ones of a certain user.
   *
   * @param userID The id of the user to to be excluded.
   * @param categoryID The category ID to search
   * @return The sequence of supplies.
   */
  def allByCategoryExceptByUser(userID: UUID, categoryID: Long): Future[Seq[Supply]]

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
   */
  def deleteRowByID(id: UUID)
}
