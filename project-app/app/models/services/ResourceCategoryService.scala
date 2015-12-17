package models.services

import java.util.UUID

import models.ResourceCategory

import scala.concurrent.Future

trait ResourceCategoryService {

  def all: Future[Seq[ResourceCategory]]

  def getCategoryIDByName(name: String) : Future[Long]

}
