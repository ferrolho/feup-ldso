package models.daos

import java.util.UUID
import javax.inject.Inject

import models.Supply
import play.api.db.slick.DatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext

/**
 * Give access to the user object using Slick
 */
class SupplyDAOImpl @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends SupplyDAO with DAOSlick {

  import driver.api._

  /**
   *
   * @return
   */
  def all = {
    val suppliesQuery = for {
      dbSupply <- slickSupplies.result
    } yield dbSupply
    db.run(suppliesQuery).map { dbSupplyOption =>
      dbSupplyOption.map { supply =>
        Supply(UUID.fromString(supply.id), UUID.fromString(supply.id), supply.resource, supply.amount)
      }
    }
  }

  /**
   * Saves a supply.
   *
   * @param supply The supply to save.
   * @return The saved supply.
   */
  def save(supply: Supply) = {
    val dbSupply = DBSupply(supply.id.toString, supply.userID.toString, supply.resource, supply.amount)

    // combine database actions to be run sequentially
    val actions = slickSupplies.insertOrUpdate(dbSupply).transactionally

    // run actions and return supply afterwards
    db.run(actions).map(_ => supply)
  }

}
