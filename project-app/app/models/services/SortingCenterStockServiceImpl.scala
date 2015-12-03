package models.services

import java.util.UUID
import javax.inject.Inject

import models.SortingCenterStock
import models.daos.SortingCenterStockDAO

import scala.concurrent.Future

/**
 * Handles actions to sorting center stock.
 *
 * @param sortingCenterStockDAO The sortingCenterStock DAO implementation.
 */
class SortingCenterStockServiceImpl @Inject()(sortingCenterStockDAO: SortingCenterStockDAO) extends SortingCenterStockService {

  /**
   * Retrieve a sorting center stock by the ID of one supply and the ID of the user in session.
   *
   * @param idSupply The id of the supply we want.
   * @param userID The id of the user in session.
   * @return The sorting center stock.
   */
  def retrieve(idSupply: UUID, userID: UUID) = sortingCenterStockDAO.find(idSupply, userID)

  /**
   * Retrieve a sorting center stock by its ID.
   *
   * @param id The id of the stock we want.
   * @return The sorting center stock.
   */
  def retrieve(id: UUID) = sortingCenterStockDAO.find(id)

  /**
   * Retrieves all sorting center stock of a certain user.
   *
   * @param userID The id of the user.
   * @return The sequence of sorting center stock.
   */
  def byUser(userID: UUID) = sortingCenterStockDAO.byUser(userID: UUID)

  /**
   * Retrieves all sorting center stock of alll except a certain user.
   *
   * @param userID The id of the user.
   * @return The sequence of sorting center stock.
   */
  def allExceptByUser(idSupply: UUID, userID: UUID) = sortingCenterStockDAO.allExceptByUser(idSupply: UUID, userID: UUID)

  /**
   * Saves am acceptable offer to store in sorting center stock
   *
   * @param sortingCenterStock the acceptable offer to save.
   * @return The saved acceptable offer.
   */
  def save(sortingCenterStock: SortingCenterStock) = sortingCenterStockDAO.save(sortingCenterStock)

  /**
   * deletes the sorting center stock with the param id.
   *
   * @param id the id of the sorting center stock.
   */
  def delete(id: UUID) = sortingCenterStockDAO.delete(id)

}
