package models.services

import javax.inject.Inject

import models.Supply
import models.daos.SupplyDAO

/**
 * Handles actions to supplies.
 *
 * @param supplyDAO The supply DAO implementation.
 */
class SupplyServiceImpl @Inject()(supplyDAO: SupplyDAO) extends SupplyService {

  def all = supplyDAO.all

  /**
   * Saves a supply.
   *
   * @param supply The supply to save.
   * @return The saved supply.
   */
  def save(supply: Supply) = supplyDAO.save(supply)

}
