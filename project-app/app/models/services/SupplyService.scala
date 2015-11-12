package models.services

import models.Supply

import scala.concurrent.Future

/**
 * Handles actions to supplies.
 */
trait SupplyService {

  /**
   * Saves a supply offer.
   *
   * @param user The user to save.
   * @return The saved user.
   */
  def save(user: Supply): Future[Supply]

}
