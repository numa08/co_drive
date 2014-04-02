package controllers

import play.api._
import play.api.mvc._

import org.pac4j.play.scala.ScalaController

object BackendApi extends ScalaController {

  def createEvent = Action(parse.json) {request =>
    for {
      title <- request.body.\("title").asOpt[String]
      repository <- request.body.\("repository").asOpt[String]
      date <- request.body.\("date").asOpt[String]
      capcity <- request.body.\("capacity").asOpt[Int]
    } yield {
      logger.debug(s"The ${title} is managed in ${repository} at ${date} capacity ${capcity}")
    }

    Ok
  }

}
