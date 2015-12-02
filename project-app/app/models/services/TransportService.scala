package models.services

import java.util.UUID

import scala.concurrent.Future
import models.Transport

trait TransportService {

  /**
   * Retrieves all transport jobs that are not accepted.
   *
   * @return The sequence of transports.
   */
  def allNonActive(): Future[Seq[Transport]]

  /**
   * Saves a Transport
   *
   * @param transport The transport that will be saved.
   * @return The saved transport.
   */
  def save(transport: Transport): Future[Transport]

}
