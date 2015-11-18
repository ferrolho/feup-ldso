package models.services


import java.util.UUID
import javax.inject.Inject

import models.{Supply, SortingCenterWarehouse}
import models.daos.SortingCenterWarehouseDAO

import scala.concurrent.Future

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
  def save(sortingCenterWarehouse:SortingCenterWarehouse) = sortingCenterWarehouseDAO.save(sortingCenterWarehouse)


}
