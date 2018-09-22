package com.example.routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.example.{ JsonSupport, UserRoutes }

trait IndexRoutes extends JsonSupport
  with UserRoutes {

  lazy val indexRoutes: Route = pathEndOrSingleSlash {
    complete("ROUTE")
  } ~ pathPrefix("users") {
    userRoutes
  } ~ pathPrefix("v2") {
    pathPrefix("users") {
      userRoutes
    }
  }
}
