package models.daos

import javax.inject.Inject

import models.ResourceAmountLabel
import play.api.db.slick.DatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext

class ResourceAmountLabelDAOImpl @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends ResourceAmountLabelDAO with DAOSlick {

  import driver.api._

  def all = {
    val query = for {
      dbSupply <- slickResourceAmountLabels.result
    } yield dbSupply

    db.run(query).map { dbOption =>
      dbOption.map { model =>
        ResourceAmountLabel(model.id, model.name)
      }
    }
  }

}
