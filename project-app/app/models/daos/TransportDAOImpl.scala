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
