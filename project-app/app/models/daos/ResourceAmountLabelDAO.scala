package models.daos

import java.util.UUID

import models.ResourceAmountLabel

import scala.concurrent.Future

trait ResourceAmountLabelDAO {

  def all: Future[Seq[ResourceAmountLabel]]

}
