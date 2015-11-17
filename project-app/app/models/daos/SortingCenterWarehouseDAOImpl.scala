package models.daos

import java.util.UUID
import javax.inject.Inject

import models.{Supply, SortingCenterWarehouse}
import play.api.db.slick.DatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future


/**
 * Give access to the sorting center warehouse object using Slick
 */
class SortingCenterWarehouseDAOImpl@Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends SortingCenterWarehouseDAO with DAOSlick {

  import driver.api._




  /**
   * Retrieves all supplies from the DB except the user's offers.
   *
   * @param userID except user
   * @return The sequence of supplies.
   */
  def allExceptMyself(userID: UUID): Future[Seq[Supply]] ={

    val suppliesQuery = for {
      dbSupply <- slickSupplies.filter(_.userID != userID.toString)
    } yield dbSupply

    db.run(suppliesQuery.result).map { dbSupplyOption =>
      dbSupplyOption.map { offer =>
        SortingCenterWarehouse(UUID.fromString(offer.id),UUID.fromString("1"), UUID.fromString(offer.userID), offer.resource, offer.amount,false)
      }
    }
  }




  /**
   * Saves a sorting center.
   *
   * @param sortingCenterWarehouse The sorting center to save.
   * @return The saved sorting center.
   */
  def save(sortingCenterWarehouse: models.SortingCenterWarehouse) = {
    val dbSortingCenterWarehouse =DBSortingCenterWarehouse(
    sortingCenterWarehouse.idResource.toString,
    sortingCenterWarehouse.idSortingCenter.toString,
    sortingCenterWarehouse.userID.toString,
    sortingCenterWarehouse.resource.toString,
    sortingCenterWarehouse.amount,
    sortingCenterWarehouse.inSortingCenter
    )

    //combine database actions to be run sequentially
    val actions = slickSortingCenterWarehouse.insertOrUpdate(dbSortingCenterWarehouse).transactionally

    //run actions and return sortingCenter afterwards
    db.run(actions).map(_ => sortingCenterWarehouse)
  }

}
