package controllers

import javax.inject.Inject

import com.mohiva.play.silhouette.api.{ Environment, LogoutEvent, Silhouette }
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import com.mohiva.play.silhouette.impl.providers.SocialProviderRegistry
import forms._
import models.{ User, AdminUsers }
import models.daos.UserDAOImpl
import play.api.i18n.MessagesApi
import scala.concurrent.Future

import play.api.mvc._

import play.api.libs.json._

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * The basic application controller.
 *
 * @param messagesApi The Play messages API.
 * @param env The Silhouette environment.
 * @param socialProviderRegistry The social provider registry.
 */
class UserController @Inject() (
  val messagesApi: MessagesApi,
  val env: Environment[User, CookieAuthenticator],
  val adminUsers: AdminUsers,
  socialProviderRegistry: SocialProviderRegistry)
  extends Silhouette[User, CookieAuthenticator] {
  
  
  def users = SecuredAction.async { implicit request =>
    adminUsers.all.map{s =>
      Ok(
        JsArray(s.map(t =>Json.obj("firstname" -> t.firstName, "lastname" -> t.lastName)))
      )
    }
  }


}
