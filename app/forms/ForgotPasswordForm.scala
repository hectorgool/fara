package forms

import play.api.data.Form
import play.api.data.Forms._

/**
 * The form which handles the sign up process.
 */
object ForgotPasswordForm {

  /**
   * A play framework form.
   */
  val emailForm = Form(single("email" -> email))

}
