package models.services

import javax.inject.Inject

import models.daos.ResourceCategoryDAO

class ResourceCategoryServiceImpl @Inject()(resourceCategoryDAO: ResourceCategoryDAO)
  extends ResourceCategoryService {

  def all = resourceCategoryDAO.all

  def getCategoryIDByName(name: String) = resourceCategoryDAO.getCategoryIDByName(name)

}
