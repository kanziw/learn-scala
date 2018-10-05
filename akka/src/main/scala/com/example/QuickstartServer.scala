package com.example

import akka.actor.{ ActorRef, ActorSystem }
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.example.data.persistence.{ MySql, TestDbAccessingRepository, mysql }
import com.example.data.repositories.UserRepositoryImpl
import com.example.routes.IndexRoutes
import com.typesafe.config.ConfigFactory

import scala.concurrent.duration.Duration
import scala.concurrent.{ Await, ExecutionContext, Future }
import scala.util.{ Failure, Success }

object QuickstartServer
  extends App
  with IndexRoutes
  with TestDbAccessingRepository
  with UserRepositoryImpl {

  implicit val system: ActorSystem = ActorSystem("helloAkkaHttpServer")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val ec: ExecutionContext = system.dispatcher

  // Configuration
  val appConfiguration = ConfigFactory.load().getConfig("akka")

  // DB
  override protected val testDb: MySql = mysql.create(appConfiguration.getConfig("mysql"))

  // Actor
  val userRegistryActor: ActorRef = system.actorOf(UserRegistryActor.props, "userRegistryActor")

  // Routes
  lazy val routes: Route = indexRoutes

  // Binding
  val serverBinding: Future[Http.ServerBinding] = Http().bindAndHandle(routes, "localhost", 8080)

  serverBinding.onComplete {
    case Success(bound) =>
      println(s"Server online at http://${bound.localAddress.getHostString}:${bound.localAddress.getPort}/")
    case Failure(e) =>
      Console.err.println(s"Server could not start!")
      e.printStackTrace()
      system.terminate()
  }

  Await.result(system.whenTerminated, Duration.Inf)
}
