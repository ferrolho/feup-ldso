package models.daos

import models.ResourceCategory

import scala.concurrent.Future

trait ResourceCategoryDAO {

  def all: Future[Seq[ResourceCategory]]

}
