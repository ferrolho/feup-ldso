package models.services

import models.ResourceAmountLabel

import scala.concurrent.Future

trait ResourceAmountLabelService {

  def all: Future[Seq[ResourceAmountLabel]]

}
