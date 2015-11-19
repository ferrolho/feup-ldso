package models.services

import models.ResourceCategory

import scala.concurrent.Future

trait ResourceCategoryService {

  def all: Future[Seq[ResourceCategory]]

}
