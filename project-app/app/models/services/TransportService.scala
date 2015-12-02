package models.services

import scala.concurrent.Future
import models.Transport


/**
 * Created by Lycantropus on 02-12-2015.
 */
trait TransportService {

  def save(user: Transport): Future[Transport]

}
