package models.daos

import models.Country

import scala.concurrent.Future

trait CountryDAO {

  def all: Future[Seq[Country]]

}
