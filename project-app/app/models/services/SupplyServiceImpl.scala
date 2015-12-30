package models.services

import java.util.UUID
import javax.inject.Inject

import models.Supply
import models.daos.SupplyDAO

/**
 * Handles actions to supplies.
 *
 * @param supplyDAO The supply DAO implementation.
 */
class SupplyServiceImpl @Inject()(supplyDAO: SupplyDAO) extends SupplyService {

  def retrieve(id: UUID) = supplyDAO.find(id)

  /**
   * Retrieves all supplies submitted by a certain user.
   *
   * @param userID The id of the user to retrieve the supplies.
   * @return The sequence of supplies.
   */
  def byUser(userID: UUID) = supplyDAO.byUser(userID: UUID)

  /**
   * Retrieves all supplies excepts the ones of a certain user.
   *
   * @param userID The id of the user to to be excluded.
   * @return The sequence of supplies.
   */
  def allExceptByUser(userID: UUID) = supplyDAO.allExceptByUser(userID: UUID)

  /**
   * Retrieves all supplies by category excepts the ones of a certain user.
   *
   * @param userID The id of the user to to be excluded.
   * @param categoryID The category ID to search
   * @return The sequence of supplies.
   */
  def allByCategoryExceptByUser(userID: UUID, categoryID: Long) = supplyDAO.allByCategoryExceptByUser(userID, categoryID)

  /**
   * Retrieves all supplies from the DB.
   *
   * @return The sequence of supplies.
   */
  def all = supplyDAO.all

  /**
   * Saves a supply.
   *
   * @param supply The supply to save.
   * @return The saved supply.
   */
  def save(supply: Supply) = supplyDAO.save(supply)

  /**
   * deletes the row with the param id ( to remove the offer of a supplier
   *
   * @param id the id of the supply to remove.
   * @return .
   */
  def deleteRowByID(id: UUID) = supplyDAO.deleteRowByID(id)

}
