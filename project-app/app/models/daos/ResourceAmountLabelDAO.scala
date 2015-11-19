package models.daos

import models.ResourceAmountLabel

import scala.concurrent.Future

trait ResourceAmountLabelDAO {

  def all: Future[Seq[ResourceAmountLabel]]

}
