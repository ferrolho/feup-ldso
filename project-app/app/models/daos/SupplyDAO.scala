package models.daos

import java.util.UUID

import models.Supply

import scala.concurrent.Future

/**
 * Give access to the supply object.
 */
trait SupplyDAO {

  /**
   * Finds a supply by its ID.
   *
   * @param id The ID of the supply to find.
   * @return The found supply or None if no supply for the given ID could be found.
   */
  def find(id: UUID): Future[Supply]

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
   * Saves a supply.
   *
   * @param supply The supply to save.
   * @return The saved supply.
   */
  def save(supply: Supply): Future[Supply]

  /**
   * deletes the row with the param id
   *
   * @param id the id of the supply to remove.
   *
   */
  def deleteRowByID(id: UUID)

}
