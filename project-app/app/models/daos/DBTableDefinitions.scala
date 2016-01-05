package models.daos

import com.mohiva.play.silhouette.api.LoginInfo
import slick.driver.JdbcProfile
import slick.lifted.ProvenShape.proveShapeOf

trait DBTableDefinitions {

  protected val driver: JdbcProfile

  import driver.api._

  // User
  case class DBUser(
                     userID: String,
                     firstName: Option[String],
                     lastName: Option[String],
                     fullName: Option[String],
                     email: Option[String],
                     avatarURL: Option[String],
                     countryID: Long,
                     city: Option[String],
                     isSupplier: Boolean = false,
                     isSortingCenter: Boolean = false,
                     isConsumer: Boolean = false,
                     isTransporter: Boolean = false
                     )

  class Users(tag: Tag) extends Table[DBUser](tag, "user") {
    def id = column[String]("userID", O.PrimaryKey)

    def firstName = column[Option[String]]("firstName")

    def lastName = column[Option[String]]("lastName")

    def fullName = column[Option[String]]("fullName")

    def email = column[Option[String]]("email")

    def avatarURL = column[Option[String]]("avatarURL")

    def countryID = column[Long]("countryID")

    def city = column[Option[String]]("city")

    def isSupplier = column[Boolean]("isSupplier");

    def isSortingCenter = column[Boolean]("isSortingCenter");

    def isConsumer = column[Boolean]("isConsumer");

    def isTransporter = column[Boolean]("isTransporter");

    def * = (id, firstName, lastName, fullName, email, avatarURL, countryID, city, isSupplier, isSortingCenter, isConsumer, isTransporter) <>(DBUser.tupled, DBUser.unapply)
  }

  // Login info
  case class DBLoginInfo(
                          id: Option[Long],
                          providerID: String,
                          providerKey: String
                          )

  class LoginInfos(tag: Tag) extends Table[DBLoginInfo](tag, "logininfo") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def providerID = column[String]("providerID")

    def providerKey = column[String]("providerKey")

    def * = (id.?, providerID, providerKey) <>(DBLoginInfo.tupled, DBLoginInfo.unapply)
  }

  // User login info
  case class DBUserLoginInfo(
                              userID: String,
                              loginInfoId: Long
                              )

  class UserLoginInfos(tag: Tag) extends Table[DBUserLoginInfo](tag, "userlogininfo") {
    def userID = column[String]("userID")

    def loginInfoId = column[Long]("loginInfoId")

    def * = (userID, loginInfoId) <>(DBUserLoginInfo.tupled, DBUserLoginInfo.unapply)
  }

  // Password info
  case class DBPasswordInfo(
                             hasher: String,
                             password: String,
                             salt: Option[String],
                             loginInfoId: Long
                             )

  class PasswordInfos(tag: Tag) extends Table[DBPasswordInfo](tag, "passwordinfo") {
    def hasher = column[String]("hasher")

    def password = column[String]("password")

    def salt = column[Option[String]]("salt")

    def loginInfoId = column[Long]("loginInfoId")

    def * = (hasher, password, salt, loginInfoId) <>(DBPasswordInfo.tupled, DBPasswordInfo.unapply)
  }

  // OAuth1 info
  case class DBOAuth1Info(
                           id: Option[Long],
                           token: String,
                           secret: String,
                           loginInfoId: Long
                           )

  class OAuth1Infos(tag: Tag) extends Table[DBOAuth1Info](tag, "oauth1info") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def token = column[String]("token")

    def secret = column[String]("secret")

    def loginInfoId = column[Long]("loginInfoId")

    def * = (id.?, token, secret, loginInfoId) <>(DBOAuth1Info.tupled, DBOAuth1Info.unapply)
  }

  // OAuth2 info
  case class DBOAuth2Info(
                           id: Option[Long],
                           accessToken: String,
                           tokenType: Option[String],
                           expiresIn: Option[Int],
                           refreshToken: Option[String],
                           loginInfoId: Long
                           )

  class OAuth2Infos(tag: Tag) extends Table[DBOAuth2Info](tag, "oauth2info") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def accessToken = column[String]("accesstoken")

    def tokenType = column[Option[String]]("tokentype")

    def expiresIn = column[Option[Int]]("expiresin")

    def refreshToken = column[Option[String]]("refreshtoken")

    def loginInfoId = column[Long]("logininfoid")

    def * = (id.?, accessToken, tokenType, expiresIn, refreshToken, loginInfoId) <>(DBOAuth2Info.tupled, DBOAuth2Info.unapply)
  }

  // Open ID info
  case class DBOpenIDInfo(
                           id: String,
                           loginInfoId: Long
                           )

  class OpenIDInfos(tag: Tag) extends Table[DBOpenIDInfo](tag, "openidinfo") {
    def id = column[String]("id", O.PrimaryKey)

    def loginInfoId = column[Long]("logininfoid")

    def * = (id, loginInfoId) <>(DBOpenIDInfo.tupled, DBOpenIDInfo.unapply)
  }

  // Open ID attribute
  case class DBOpenIDAttribute(
                                id: String,
                                key: String,
                                value: String
                                )

  class OpenIDAttributes(tag: Tag) extends Table[DBOpenIDAttribute](tag, "openidattributes") {
    def id = column[String]("id")

    def key = column[String]("key")

    def value = column[String]("value")

    def * = (id, key, value) <>(DBOpenIDAttribute.tupled, DBOpenIDAttribute.unapply)
  }

  /*
   * Our tables.
   */

  // Countries
  case class DBCountry(
                        id: Long,
                        name: String)

  class Countries(tag: Tag) extends Table[DBCountry](tag, "country") {
    def id = column[Long]("id", O.PrimaryKey)

    def name = column[String]("name")

    def * = (id, name) <>(DBCountry.tupled, DBCountry.unapply)
  }

  // Supplies
  case class DBSupply(
                       id: String,
                       userID: String,
                       resource: String,
                       resourceCategoryID: Long,
                       amount: Int,
                       amountLabelID: Long
                       )

  class Supplies(tag: Tag) extends Table[DBSupply](tag, "supply") {
    def id = column[String]("id", O.PrimaryKey)

    def userID = column[String]("userID")

    def resource = column[String]("resource")

    def resourceCategoryID = column[Long]("resourceCategoryID")

    def amount = column[Int]("amount")

    def amountLabelID = column[Long]("amountLabelID")

    def * = (id, userID, resource, resourceCategoryID, amount, amountLabelID) <>(DBSupply.tupled, DBSupply.unapply)
  }

  // Resource categories
  case class DBResourceCategory(
                                 id: Long,
                                 name: String
                                 )

  class ResourceCategories(tag: Tag) extends Table[DBResourceCategory](tag, "resourceCategory") {
    def id = column[Long]("id", O.PrimaryKey)

    def name = column[String]("name")

    def * = (id, name) <>(DBResourceCategory.tupled, DBResourceCategory.unapply)
  }

  // Resource amount labels
  case class DBResourceAmountLabel(
                                    id: Long,
                                    name: String
                                    )

  class ResourceAmountLabels(tag: Tag) extends Table[DBResourceAmountLabel](tag, "resourceAmountLabel") {
    def id = column[Long]("id", O.PrimaryKey)

    def name = column[String]("name")

    def * = (id, name) <>(DBResourceAmountLabel.tupled, DBResourceAmountLabel.unapply)
  }

  // Sorting center stock
  case class DBSortingCenterStock(
                                   id: String,
                                   idSupply: String,
                                   userID: String,
                                   supplyUserID: String,
                                   resource: String,
                                   resourceCategoryID: Long,
                                   amount: Int,
                                   amountLabelID: Long
                                   )

  class SortingCenterStocks(tag: Tag) extends Table[DBSortingCenterStock](tag, "sortingCenterStock") {
    def id = column[String]("id", O.PrimaryKey)

    def idSupply = column[String]("idSupply")

    def userID = column[String]("userID")

    def supplyUserID = column[String]("supplyUserID")

    def resource = column[String]("resource")

    def resourceCategoryID = column[Long]("resourceCategoryID")

    def amount = column[Int]("amount")

    def amountLabelID = column[Long]("amountLabelID")

    def * = (id, idSupply, userID, supplyUserID, resource, resourceCategoryID, amount, amountLabelID) <>(DBSortingCenterStock.tupled, DBSortingCenterStock.unapply)
  }

  // table query definitions
  val slickUsers = TableQuery[Users]
  val slickLoginInfos = TableQuery[LoginInfos]
  val slickUserLoginInfos = TableQuery[UserLoginInfos]
  val slickPasswordInfos = TableQuery[PasswordInfos]
  val slickOAuth1Infos = TableQuery[OAuth1Infos]
  val slickOAuth2Infos = TableQuery[OAuth2Infos]
  val slickOpenIDInfos = TableQuery[OpenIDInfos]
  val slickOpenIDAttributes = TableQuery[OpenIDAttributes]

  val slickCountries = TableQuery[Countries]
  val slickSupplies = TableQuery[Supplies]
  val slickResourceCategories = TableQuery[ResourceCategories]
  val slickResourceAmountLabels = TableQuery[ResourceAmountLabels]
  val slickSortingCenterStocks = TableQuery[SortingCenterStocks]

  // queries used in multiple places
  def loginInfoQuery(loginInfo: LoginInfo) =
    slickLoginInfos.filter(dbLoginInfo => dbLoginInfo.providerID === loginInfo.providerID && dbLoginInfo.providerKey === loginInfo.providerKey)

}
