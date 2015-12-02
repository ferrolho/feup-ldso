package controllers

import javax.inject.Inject

import com.mohiva.play.silhouette.api.{Environment, Silhouette}
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import models.{SortingCenterStock, User, Transport}
import play.api.i18n.MessagesApi
import models.services.{ResourceAmountLabelService, ResourceCategoryService, SortingCenterStockService, TransportService}
import models.daos.UserDAO

import scala.collection.mutable.ListBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 *
 * @param messagesApi
 * @param env
 */
class TransportController @Inject()(
                                     val messagesApi: MessagesApi,
                                     val env: Environment[User, CookieAuthenticator],
                                     sortingCenterStockService: SortingCenterStockService,
                                     resourceCategoryService: ResourceCategoryService,
                                     resourceAmountLabelService: ResourceAmountLabelService,
                                     transportService: TransportService,
                                     userDAO: UserDAO)
  extends Silhouette[User, CookieAuthenticator] {

  def index = SecuredAction.async { implicit request =>

    var sourceEmailsList = new ListBuffer[String]()
    var destinyEmailsList = new ListBuffer[String]()
    var sortingCenterStockList = new ListBuffer[SortingCenterStock]()
    val transportsList = new ListBuffer[Transport]()

    // TODO ask Sereno for a better way to do this
    transportService.allNonActive().map { transports =>
      transports.map { transport =>

        transportsList += transport

        // gets email from source
        userDAO.find(transport.idSourceUser).map { option =>
          option.map { user =>
            user.email.map { email =>
              sourceEmailsList += email
            }
          }
        }

        // gets email from destiny
        userDAO.find(transport.idDestinyUser).map { option =>
          option.map { user =>
            user.email.map { email =>
              sourceEmailsList += email
            }
          }
        }

        // gets SC of this transport
        sortingCenterStockService.retrieve(transport.idSCStock)
      }
    }

      Future.successful(Ok(views.html.transports.index(request.identity, sourceEmailsList.toList, destinyEmailsList.toList, sortingCenterStockList.toList, transportsList.toList)))
  }
}
