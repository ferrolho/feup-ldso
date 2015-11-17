package models.daos

import java.util.UUID

import models.{SortingCenterWarehouse}

import scala.concurrent.Future
import models.Supply


trait SortingCenterWarehouseDAO {




  /**
   * Retrieves all supplies from the DB except the user's offers.
   *
   * @param userID except user
   * @return The sequence of supplies.
   */
  def allExceptMyself(userID: UUID): Future[Seq[Supply]]

  /**
   * Saves a SortingCenter.
   *
   * @param sortingCenterWarehouse The sorting center to save.
   * @return The saved sorting center.
   */
  def save(sortingCenterWarehouse: SortingCenterWarehouse): Future[SortingCenterWarehouse]


}
