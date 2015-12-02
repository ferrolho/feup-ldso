package models.services

import javax.inject.Inject

import models.Transport
import models.daos.TransportDAO

/**
 * Created by Lycantropus on 02-12-2015.
 */
class TransportServiceImpl @Inject()(transportDAO: TransportDAO) extends TransportService{

  def save(transport: Transport) = transportDAO.save(transport)
}
