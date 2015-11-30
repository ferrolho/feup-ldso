package models.services

import javax.inject.Inject

import models.daos.CountryDAO

class CountryServiceImpl @Inject()(countryDAO: CountryDAO)
  extends CountryService {

  def all = countryDAO.all

}
