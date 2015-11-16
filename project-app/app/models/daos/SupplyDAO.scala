package models.daos

import models.Supply

import scala.concurrent.Future

/**
 * Give access to the supply object.
 */
trait SupplyDAO {

  /**
   *
   * @return
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
