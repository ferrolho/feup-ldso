package models.services

import java.util.UUID

import models.{Supply, SortingCenterWarehouse}

import scala.concurrent.Future

/**
 * Handles actions to sorting center warehouse.
 */
trait SortingCenterWarehouseService {

  /**
   * Saves am acceptable offer to store in sorting center warehouse
   *
   * @param sortingCenterWarehouse the acceptable offer to save.
   * @return The saved acceptable offer.
   */
  def save(sortingCenterWarehouse: SortingCenterWarehouse): Future[SortingCenterWarehouse]

}
