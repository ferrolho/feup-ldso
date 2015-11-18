package models.services

import models.SortingCenterStock

import scala.concurrent.Future

/**
 * Handles actions to sorting center stock.
 */
trait SortingCenterStockService {

  /**
   * Saves am acceptable offer to store in sorting center stock
   *
   * @param sortingCenterStock the acceptable offer to save.
   * @return The saved acceptable offer.
   */
  def save(sortingCenterStock: SortingCenterStock): Future[SortingCenterStock]

}
