package models.daos

import java.util.UUID
import javax.inject.Inject

import models.ResourceCategory
import play.api.db.slick.DatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext

class ResourceCategoryDAOImpl @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends ResourceCategoryDAO with DAOSlick {

  import driver.api._

  def all = {
    val query = for {
      dbSupply <- slickResourceCategories.result
    } yield dbSupply

    db.run(query).map { dbOption =>
      dbOption.map { model =>
        ResourceCategory(model.id, model.name)
      }
    }
  }

}
