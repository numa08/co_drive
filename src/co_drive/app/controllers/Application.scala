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
      val urlGitHub = getRedirectAction(request, newSession, "GitHubClient" , "/").getLocation
      views.html.githubloginbutton(urlGitHub)
    }
    Ok(views.html.index(navbarButton))
  }


  def createEvent = Action { request =>
    val newSession = getOrCreateSessionId(request)
    Option(getUserProfile(request)).map { p =>
      val avatarUrl = p.getAttribute("avatar_url").toString()
      val screenName = p.getAttribute("login").toString()
      val token = p.getAttribute("access_token").toString
      val navBar = views.html.githubuserbutton(screenName, avatarUrl)
      Ok(views.html.createEvent(navBar, screenName, token))

    }.getOrElse{
      val urlGitHub = getRedirectAction(request, newSession, "GitHubClient", "/event/admin/new").getLocation
      val content = views.html.unauthorized(views.html.githubloginbutton(urlGitHub))
      Unauthorized(content)
    }
  }
}
