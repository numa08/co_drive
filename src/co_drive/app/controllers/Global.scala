package controllers

import play.api.{Configuration, GlobalSettings}
import play.api.mvc.{Results, SimpleResult, RequestHeader}
import scala.concurrent.Future
import org.pac4j.oauth.client.GitHubClient
import org.pac4j.core.client.Clients
import org.pac4j.play.Config
import com.typesafe.config.ConfigFactory
import java.io.File

object Global extends GlobalSettings {

  override def onError(request : RequestHeader, ex : Throwable) : Future[SimpleResult] = {
    Future.successful(Results.InternalServerError)
  }

  override def onStart(app : play.api.Application) : Unit = {
    val config = Configuration(ConfigFactory.load(ConfigFactory.parseFileAnySyntax(new File("conf/pac4j.conf"))))
    for {
      ghId     <- config.getString("client_id")
      ghSecret <- config.getString("client_secret")
      baseUrl  <- config.getString("baseUrl")
    } yield {
      val ghClinet = new GitHubClient(ghId, ghSecret)
      val clients = new Clients(baseUrl + "/callback", ghClinet)
      Config.setClients(clients)
    }
  }
}
