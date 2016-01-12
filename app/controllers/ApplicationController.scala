package controllers

import javax.inject.Inject

import com.mohiva.play.silhouette.api.{ Environment, LogoutEvent, Silhouette }
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import com.mohiva.play.silhouette.impl.providers.SocialProviderRegistry
import forms._
import models.User
import models.daos.UserDAOImpl
import play.api.i18n.MessagesApi
import scala.concurrent.Future

import play.api.mvc._

import play.api.libs.json._

/**
 * The basic application controller.
 *
 * @param messagesApi The Play messages API.
 * @param env The Silhouette environment.
 * @param socialProviderRegistry The social provider registry.
 */
class ApplicationController @Inject() (
  val messagesApi: MessagesApi,
  val env: Environment[User, CookieAuthenticator],
  socialProviderRegistry: SocialProviderRegistry)
  extends Silhouette[User, CookieAuthenticator] {


  /**
   * Handles the index action.
   *
   * @return The result to display.
   */
  def index = SecuredAction.async { implicit request =>
    Future.successful(Ok(views.html.home(request.identity)))
  }

  /**
   * Handles the Sign In action.
   *
   * @return The result to display.
   */
  def signIn = UserAwareAction.async { implicit request =>
    request.identity match {
      case Some(user) => Future.successful(Redirect(routes.ApplicationController.index()))
      case None => Future.successful(Ok(views.html.signIn(SignInForm.form, socialProviderRegistry)))
    }
  }

  /**
   * Handles the Sign Up action.
   *
   * @return The result to display.
   */
  def signUp = UserAwareAction.async { implicit request =>
    request.identity match {
      case Some(user) => Future.successful(Redirect(routes.ApplicationController.index()))
      case None => Future.successful(Ok(views.html.signUp(SignUpForm.form)))
    }
  }

  /**
   * Handles the Sign Out action.
   *
   * @return The result to display.
   */
  def signOut = SecuredAction.async { implicit request =>
    val result = Redirect(routes.ApplicationController.index())
    env.eventBus.publish(LogoutEvent(request.identity, request, request2Messages))

    env.authenticatorService.discard(request.authenticator, result)
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // FORGOT PASSWORD
  /**
   * Starts the reset password mechanism if the user has forgot his password. It shows a form to insert his email address.
   */
  /*
  def forgotPassword = UserAwareAction.async { implicit request =>
    Future.successful(request.identity match {
      case Some(_) => 
        Redirect(routes.ApplicationController.index)
      case None => 
        Ok(views.html.forgotPassword(ForgotPasswordForm.emailForm))
    })
  }
  */

  /**
   * Sends an email to the user with a link to reset the password
   */
  /*
  def handleForgotPassword = Action.async { implicit request =>
    ForgotPasswordForm.emailForm.bindFromRequest.fold(
      formWithErrors => 
        Future.successful(BadRequest(views.html.forgotPassword(formWithErrors))),
      email => 
        env.identityService.retrieve(email).flatMap {
          case Some(_) => {
            val token = MailTokenUser(email, isSignUp = false)
            env.tokenService.create(token).map { _ =>
              Mailer.forgotPassword(email, link = routes.Auth.resetPassword(token.id).absoluteURL())
              Ok(views.html.forgotPasswordSent(email))
            }
          }
          case None => 
            Future.successful(BadRequest(viewsAuth.forgotPassword(ForgotPasswordForm.emailForm.withError("email", Messages("auth.user.notexists")))))
        }
    )
  }  
  */

  //beta
  /*
  def users = Action.async{
    UserDAOImpl.findAll.map{s =>
      Ok(
        JsArray(s.map(t =>Json.obj("firstname" -> t._3, "lastname" -> t._4)))
      )
    }
  }
  */


}
