package controllers

import play.api.mvc._
import org.pac4j.play.scala._

object Application extends ScalaController{

  def index = Action { request =>
    val newSession = getOrCreateSessionId(request)
    val navbarButton = Option(getUserProfile(request)).map{p =>
      val avatarUrl = p.getAttribute("avatar_url").toString
      val screenName = p.getAttribute("login").toString
      views.html.githubuserbutton(screenName, avatarUrl)
    }.getOrElse{
      val urlGithub = getRedirectionUrl(request, newSession, "GitHubClient", "/?0")
      views.html.githubloginbutton(urlGithub)
    }
    Ok(views.html.index(navbarButton))
  }

}
