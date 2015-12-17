package models.daos

import javax.inject.Inject

import models.ResourceCategory
import play.api.db.slick.DatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future

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

  def getCategoryIDByName(name: String) = {
    val query = for {
      dbCategory <- slickResourceCategories.filter(_.name === name)
    } yield dbCategory

    db.run(query.result.headOption).map { categoryOption =>
      categoryOption.get.id
    }
  }
}
