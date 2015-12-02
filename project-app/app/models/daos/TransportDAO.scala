package models.daos

import scala.concurrent.Future
import models.Transport

trait TransportDAO {

  /**
   * Saves a Transport.
   *
   * @param transport The transport to save.
   * @return The saved transport.
   */
  def save(transport: Transport): Future[Transport]
}
