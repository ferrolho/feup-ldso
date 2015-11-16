package models.services

import java.util.UUID

import models.Supply

import scala.concurrent.Future

/**
 * Handles actions to supplies.
 */
trait SupplyService {

  def byUser(userID: UUID): Future[Seq[Supply]]

  def all: Future[Seq[Supply]]

  /**
   * Saves a supply offer.
   *
   * @param user The user to save.
   * @return The saved user.
   */
  def save(user: Supply): Future[Supply]

}
