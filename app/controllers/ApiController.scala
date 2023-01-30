package controllers

import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.{Inject, Singleton}

@Singleton
class ApiController @Inject()(cc: ControllerComponents)
      extends AbstractController(cc) {

  def ping = Action { implicit request2flash =>
    Ok("Ping Response")
  }

}
