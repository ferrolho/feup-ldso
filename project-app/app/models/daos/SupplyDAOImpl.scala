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
   * Finds a supply by its ID.
   *
   * @param id The ID of the supply to find.
   * @return The found supply or None if no supply for the given ID could be found.
   */
  def find(id: UUID) = {
    val query = for {
      dbSupply <- slickSupplies.filter(_.id === id.toString)
    } yield dbSupply

    db.run(query.result.headOption).map { supplyOption =>
      supplyOption.map { supply =>
        Supply(
          UUID.fromString(supply.id),
          UUID.fromString(supply.userID),
          supply.resource,
          supply.resourceCategoryID,
          supply.amount,
          supply.amountLabelID
        )
      }
    }
  }

  /**
   * Retrieves all supplies submitted by a certain user.
   *
   * @param userID The id of the user to retrieve the supplies.
   * @return The sequence of supplies.
   */
  def byUser(userID: UUID) = {
    val suppliesQuery = for {
      dbSupply <- slickSupplies.filter(_.userID === userID.toString)
    } yield dbSupply

    db.run(suppliesQuery.result).map { dbSupplyOption =>
      dbSupplyOption.map { supply =>
        Supply(
          UUID.fromString(supply.id),
          UUID.fromString(supply.userID),
          supply.resource,
          supply.resourceCategoryID,
          supply.amount,
          supply.amountLabelID
        )
      }
    }
  }

  /**
   * Retrieves all supplies excepts the ones of a certain user from the DB.
   *
   * @param userID The id of the user to to be excluded.
   * @return The sequence of supplies.
   */
  def allExceptByUser(userID: UUID) = {
    val suppliesQuery = for {
      dbSupply <- slickSupplies.filter(_.userID =!= userID.toString)
    } yield dbSupply

    db.run(suppliesQuery.result).map { dbSupplyOption =>
      dbSupplyOption.map { supply =>
        Supply(
          UUID.fromString(supply.id),
          UUID.fromString(supply.userID),
          supply.resource,
          supply.resourceCategoryID,
          supply.amount,
          supply.amountLabelID
        )
      }
    }
  }

  /**
   * Retrieves all supplies by category excepts the ones of a certain user from the DB.
   *
   * @param userID The id of the user to to be excluded.
   * @param categoryID The category ID to search
   * @return The sequence of supplies.
   */
  def allByCategoryExceptByUser(userID: UUID, categoryID: Long) = {
    val suppliesQuery = for {
      dbSupply <- slickSupplies.filter(x => x.userID =!= userID.toString && x.resourceCategoryID === categoryID)
    } yield dbSupply

    db.run(suppliesQuery.result).map { dbSupplyOption =>
      dbSupplyOption.map { supply =>
        Supply(
          UUID.fromString(supply.id),
          UUID.fromString(supply.userID),
          supply.resource,
          supply.resourceCategoryID,
          supply.amount,
          supply.amountLabelID
        )
      }
    }
  }

  /**
   * Retrieves all supplies from the DB.
   *
   * @return The sequence of supplies.
   */
  def all = {
    val suppliesQuery = for {
      dbSupply <- slickSupplies.result
    } yield dbSupply

    db.run(suppliesQuery).map { dbSupplyOption =>
      dbSupplyOption.map { supply =>
        Supply(
          UUID.fromString(supply.id),
          UUID.fromString(supply.userID),
          supply.resource,
          supply.resourceCategoryID,
          supply.amount,
          supply.amountLabelID)
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
    val dbSupply = DBSupply(
      supply.id.toString,
      supply.userID.toString,
      supply.resource,
      supply.resourceCategoryID,
      supply.amount,
      supply.amountLabelID)

    // combine database actions to be run sequentially
    val actions = slickSupplies.insertOrUpdate(dbSupply).transactionally

    // run actions and return supply afterwards
    db.run(actions).map(_ => supply)
  }

  /**
   * deletes the row with the param id
   *
   * @param id the id of the supply to remove.
   *
   */
  def deleteRowByID(id: UUID) {
    db.run(slickSupplies.filter(_.id === id.toString).delete)
  }

}
