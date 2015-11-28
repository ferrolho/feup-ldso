package models.services

import models.Country

import scala.concurrent.Future

trait CountryService {

  def all: Future[Seq[Country]]

}
