package com.example.data.repositories

import com.example.data.persistence.TestDbAccessingRepository
import com.example.data.persistence.mysql.entities.User

import scala.concurrent.Future

trait UserRepositoryImpl extends UserRepository {
  this: TestDbAccessingRepository =>

  import testDb._

  override def getUsers: Future[List[User]] = {
    testDb.run {
      quote {
        query[User].filter(_.name != "anonymous")
      }
    }
  }

  override def createUser(name: String, email: String, password: String = "123456"): Future[Unit] = {
    testDb.run {
      quote {
        query[User].insert(lift(User(name, email, password)))
      }
    }.map(_ => Unit)
  }
}
