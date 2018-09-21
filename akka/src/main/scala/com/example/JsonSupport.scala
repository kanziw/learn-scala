package com.example

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.example.UserRegistryActor.ActionPerformed
import spray.json.{ DefaultJsonProtocol, RootJsonFormat }

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val userJsonFormat: RootJsonFormat[User] = jsonFormat3(User)
  implicit val usersJsonFormat: RootJsonFormat[Users] = jsonFormat1(Users)

  implicit val actionPerformedJsonFormat: RootJsonFormat[ActionPerformed] =
    jsonFormat1(ActionPerformed)
}
