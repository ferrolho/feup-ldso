package models.daos

import javax.inject.Inject

import models.Transport
import play.api.Logger
import play.api.db.slick.DatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext

class TransportDAOImpl @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
extends TransportDAO with DAOSlick {


  import driver.api._
  /**
   * Saves a transport.
   *
   * @param transport The transport to save.
   * @return The saved transport.
   */
  def save(transport: Transport) = {
    Logger.debug(s"PUTA ENTREI")

    Logger.debug(s"ID=${transport.id}")
    Logger.debug(s"IDSO=${transport.idSourceUser}")
    Logger.debug(s"IDDE=${transport.idDestinyUser}")
    Logger.debug(s"IDSTOC=${transport.idSCStock}")
    Logger.debug(s"BOO=${transport.active}")
    Logger.debug(s"IDTRANS=${transport.idTransporter}")

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
