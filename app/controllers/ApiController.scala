package controllers

import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}
import repositories.DataRepository

import javax.inject.{Inject, Singleton}

@Singleton
class ApiController @Inject()(cc: ControllerComponents,
                              dataRepository: DataRepository)
      extends AbstractController(cc) {

  def ping = Action { implicit request2flash =>
    Ok("Ping Response")
  }

  def getPost(postId: Int) = Action { implicit request =>
    dataRepository.getPost(postId)
      .map( post => Ok(Json.toJson(post))).getOrElse(NotFound)
  }

  def getComments(postId: Int) = Action { implicit request =>
    Ok(Json.toJson(dataRepository.getComments(postId)))
  }

}
