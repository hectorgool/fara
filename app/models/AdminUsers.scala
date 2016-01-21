package models


import models.daos._
import play.api.db.slick.DatabaseConfigProvider
import javax.inject.Inject
import scala.concurrent.Future


class AdminUsers @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends DAOSlick {


	import driver.api._

	private val Users = slickUsers

	def all() = db.run(Users.result)

	
}