package models.daos

import javax.inject.Inject

import models.Country
import play.api.db.slick.DatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext

class CountryDAOImpl @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends CountryDAO with DAOSlick {

  import driver.api._

  def all = {
    val query = for {
      dbSupply <- slickCountries.result
    } yield dbSupply

    db.run(query).map { dbOption =>
      dbOption.map { model =>
        Country(model.id, model.name)
      }
    }
  }

}
