package models.services

import javax.inject.Inject

import models.SortingCenterWarehouse
import models.daos.SortingCenterWarehouseDAO

/**
 * Handles actions to sorting center warehouse.
 *
 * @param sortingCenterWarehouseDAO The sortingCenterWarehouse DAO implementation.
 */
class SortingCenterWarehouseServiceImpl @Inject()(sortingCenterWarehouseDAO: SortingCenterWarehouseDAO) extends SortingCenterWarehouseService {

  /**
   * Saves am acceptable offer to store in sorting center warehouse
   *
   * @param sortingCenterWarehouse the acceptable offer to save.
   * @return The saved acceptable offer.
   */
  def save(sortingCenterWarehouse: SortingCenterWarehouse) = sortingCenterWarehouseDAO.save(sortingCenterWarehouse)

}
