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

  def byUser(userID: UUID) = {
    val suppliesQuery = for {
      dbSupply <- slickSupplies.filter(_.userID === userID.toString)
    } yield dbSupply

    db.run(suppliesQuery.result).map { dbSupplyOption =>
      dbSupplyOption.map { supply =>
        Supply(UUID.fromString(supply.id), UUID.fromString(supply.userID), supply.resource, supply.amount)
      }
    }
  }

  def all = {
    val suppliesQuery = for {
      dbSupply <- slickSupplies.result
    } yield dbSupply

    db.run(suppliesQuery).map { dbSupplyOption =>
      dbSupplyOption.map { supply =>
        Supply(UUID.fromString(supply.id), UUID.fromString(supply.userID), supply.resource, supply.amount)
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
