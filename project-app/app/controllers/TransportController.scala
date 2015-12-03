package controllers

import javax.inject.Inject

import com.mohiva.play.silhouette.api.{Environment, Silhouette}
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import models.{SortingCenterStock, User, Transport}
import play.api.Logger
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

    // TODO ask Sereno for a better way to do this
    transportService.allNonActive().map { transports =>

      var sourceEmailsListBuffer = ListBuffer[String]()
      var destinyEmailsListBuffer = ListBuffer[String]()
      var sortingCenterStockListBuffer = ListBuffer[SortingCenterStock]()
      var transportsListBuffer = ListBuffer[Transport]()

      transports.map { transport =>

        transportsListBuffer.append(transport)
        Logger.debug(s"TRANSP LIST SIZE: ${transportsListBuffer.size}")

        // gets email from source
        userDAO.find(transport.idSourceUser).map { option =>
          option.map { user =>
            user.email.map { email =>
              sourceEmailsListBuffer.append(email)
            }
          }
        }

        // gets email from destiny
        userDAO.find(transport.idDestinyUser).map { option =>
          option.map { user =>
            user.email.map { email =>
              destinyEmailsListBuffer.append(email)
            }
          }
        }

        // gets SC of this transport
        sortingCenterStockService.retrieve(transport.idSCStock).map { sortingCenterStock =>
          sortingCenterStockListBuffer.append(sortingCenterStock)
        }
      }

      Ok(views.html.transports.index(request.identity, sourceEmailsListBuffer.toList, destinyEmailsListBuffer.toList, sortingCenterStockListBuffer.toList, transportsListBuffer.toList))
    }
  }
}
