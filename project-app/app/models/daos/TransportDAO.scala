package models.daos

import java.util.UUID

import scala.concurrent.Future
import models.Transport

trait TransportDAO {

  /**
   * Retrieves all non active transports from DB.
   *
   * @return The sequence of transports.
   */
  def allNonActive(): Future[Seq[Transport]]

  /**
   * Saves a Transport.
   *
   * @param transport The transport to save.
   * @return The saved transport.
   */
  def save(transport: Transport): Future[Transport]
}
