package models.daos

import java.util.UUID
import javax.inject.Inject

import models.Transport
import play.api.db.slick.DatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext

class TransportDAOImpl @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
extends TransportDAO with DAOSlick {

  import driver.api._

  /**
   * Retrieves all non active transports from DB.
   *
   * @return The sequence of transports.
   */
  def allNonActive() = {
    val transportsQuery = for {
      dbTransports <- slickTransports.filter(_.active === false)
    } yield dbTransports

    db.run(transportsQuery.result).map { dbTransportsOption =>
      dbTransportsOption.map { transport =>
        Transport(
          UUID.fromString(transport.id),
          UUID.fromString(transport.idSourceUser),
          UUID.fromString(transport.idDestinyUser),
          UUID.fromString(transport.idSCStock),
          transport.active,
          UUID.fromString(transport.idTransporter)
        )
      }
    }
  }

  /**
   * Saves a transport.
   *
   * @param transport The transport to save.
   * @return The saved transport.
   */
  def save(transport: Transport) = {

    val dbTransport = DBTransport(
      transport.id.toString,
      transport.idSourceUser.toString,
      transport.idDestinyUser.toString,
      transport.idSCStock.toString,
      transport.active,
      transport.idTransporter.toString
    )

    //combine database actions to be run sequentially
    val actions = slickTransports.insertOrUpdate(dbTransport).transactionally

    //run actions and return sortingCenter afterwards
    db.run(actions).map(_ => transport)
  }
}
