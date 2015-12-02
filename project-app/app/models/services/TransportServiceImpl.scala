package models.services

import java.util.UUID
import javax.inject.Inject

import models.Transport
import models.daos.TransportDAO

import scala.concurrent.Future

class TransportServiceImpl @Inject()(transportDAO: TransportDAO) extends TransportService{

  /**
   * Retrieves all transport jobs that are not accepted.
   *
   * @return The sequence of transports.
   */
  def allNonActive() = transportDAO.allNonActive()

  /**
   * Saves a Transport
   *
   * @param transport The transport that will be saved.
   * @return The saved transport.
   */
  def save(transport: Transport) = transportDAO.save(transport)
}
