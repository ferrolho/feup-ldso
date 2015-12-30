package models.daos

import java.util.UUID

import models.ResourceCategory

import scala.concurrent.Future

trait ResourceCategoryDAO {

  def all: Future[Seq[ResourceCategory]]

  def getCategoryIDByName(name: String) : Future[Long]

}
