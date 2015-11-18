package models.services

import javax.inject.Inject

import models.SortingCenterStock
import models.daos.SortingCenterStockDAO

/**
 * Handles actions to sorting center stock.
 *
 * @param sortingCenterStockDAO The sortingCenterStock DAO implementation.
 */
class SortingCenterStockServiceImpl @Inject()(sortingCenterStockDAO: SortingCenterStockDAO) extends SortingCenterStockService {

  /**
   * Saves am acceptable offer to store in sorting center stock
   *
   * @param sortingCenterStock the acceptable offer to save.
   * @return The saved acceptable offer.
   */
  def save(sortingCenterStock: SortingCenterStock) = sortingCenterStockDAO.save(sortingCenterStock)

}
