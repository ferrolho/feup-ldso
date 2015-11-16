package models.daos

import java.util.UUID

import models.Supply

import scala.concurrent.Future

/**
 * Give access to the supply object.
 */
trait SupplyDAO {

  /**
   * Retrieves all supplies submitted by a certain user.
   *
   * @param userID The id of the user to retrieve the supplies.
   * @return The sequence of supplies.
   */
  def byUser(userID: UUID): Future[Seq[Supply]]

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

}
