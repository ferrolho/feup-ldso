package controllers

import javax.inject.Inject

import com.mohiva.play.silhouette.api.{Environment, Silhouette}
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import models.{SortingCenterStock, User, Transport}
import play.api.Logger
import play.api.i18n.MessagesApi
import models.services.{ResourceAmountLabelService, ResourceCategoryService, SortingCenterStockService, TransportService}
import models.daos.{UserDAO}

import scala.collection.mutable.ListBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

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

      val sourceEmailsListBuffer: ListBuffer[String] = new ListBuffer[String]()
      val destinyEmailsListBuffer = new ListBuffer[String]()
      val sortingCenterStockListBuffer = new ListBuffer[SortingCenterStock]()
      val transportsListBuffer = new ListBuffer[Transport]()

      for (transport <- transports) {

        // gets email from source
        userDAO.find(transport.idSourceUser).map { option =>
          Logger.debug(s"Entry on Source Email")
          sourceEmailsListBuffer.append(option.get.email.get)
        }

        // gets email from destiny
        userDAO.find(transport.idDestinyUser).map {
          case Some(user) =>
            user.email.map( email => destinyEmailsListBuffer.append(email))
        }

        // gets SC of this transport
        /*sortingCenterStockService.retrieve(transport.idSCStock).onComplete {
          case Success(sortingCenterStock) =>
            Logger.debug(s"Entry on SCS")
            sortingCenterStockListBuffer.append(sortingCenterStock)
            Logger.debug(s"SortingCenterBuffer Last: ${sortingCenterStockListBuffer.last}")
        }*/

        Logger.debug(s"Entry on Transport")
        transportsListBuffer.append(transport)
      }

      val sourceEmailsList = sourceEmailsListBuffer.toList
      val destinyEmailsList = destinyEmailsListBuffer.toList
      //val sortingCenterStockList = sortingCenterStockListBuffer.toList
      val transportsList = transportsListBuffer.toList

      Logger.debug(s"Size of source emails buffer: ${sourceEmailsListBuffer.size}")
      Logger.debug(s"Size of destiny emails buffer: ${destinyEmailsListBuffer.size}")
      //Logger.debug(s"Size of scs buffer: ${sortingCenterStockListBuffer.size}")
      Logger.debug(s"Size of transp buffer: ${transportsListBuffer.size}")

      //Logger.debug(s"SortingCenterTotalListBuffer: ${sortingCenterStockListBuffer}")

      Logger.debug(s"Size of source emails: ${sourceEmailsList.size}")
      Logger.debug(s"Size of destiny emails: ${destinyEmailsList.size}")
      //Logger.debug(s"Size of scs: ${sortingCenterStockList.size}")
      Logger.debug(s"Size of transp: ${transportsList.size}")

      //Logger.debug(s"SortingCenterTotalList: ${sortingCenterStockList}")

      Ok(views.html.transports.index(request.identity, sourceEmailsList, destinyEmailsListBuffer.toList, transportsList))
    }
  }
}
