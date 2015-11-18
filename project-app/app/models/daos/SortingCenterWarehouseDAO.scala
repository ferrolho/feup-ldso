package models.daos

import models.SortingCenterWarehouse

import scala.concurrent.Future

trait SortingCenterWarehouseDAO {

  /**
   * Saves a SortingCenter.
   *
   * @param sortingCenterWarehouse The sorting center to save.
   * @return The saved sorting center.
   */
  def save(sortingCenterWarehouse: SortingCenterWarehouse): Future[SortingCenterWarehouse]

}
