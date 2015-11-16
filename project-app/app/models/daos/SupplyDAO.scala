package models.daos

import java.util.UUID

import models.Supply

import scala.concurrent.Future

/**
 * Give access to the supply object.
 */
trait SupplyDAO {

  def byUser(userID: UUID): Future[Seq[Supply]]

  def all: Future[Seq[Supply]]

  /**
   * Saves a supply.
   *
   * @param supply The supply to save.
   * @return The saved supply.
   */
  def save(supply: Supply): Future[Supply]

}
