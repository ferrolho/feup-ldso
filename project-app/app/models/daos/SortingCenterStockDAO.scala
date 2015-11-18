package models.daos

import models.SortingCenterStock

import scala.concurrent.Future

trait SortingCenterStockDAO {

  /**
   * Saves a SortingCenter.
   *
   * @param sortingCenterStock The sorting center to save.
   * @return The saved sorting center.
   */
  def save(sortingCenterStock: SortingCenterStock): Future[SortingCenterStock]

}
