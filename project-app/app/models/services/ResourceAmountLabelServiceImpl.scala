package models.services

import javax.inject.Inject

import models.daos.ResourceAmountLabelDAO

class ResourceAmountLabelServiceImpl @Inject()(resourceAmountLabelDAO: ResourceAmountLabelDAO)
  extends ResourceAmountLabelService {

  def all = resourceAmountLabelDAO.all

}
