package com.example.data.repositories

import com.example.data.persistence.mysql.entities.User

import scala.concurrent.Future

trait UserRepository {
  def getUsers: Future[List[User]]

  def createUser(name: String, email: String, password: String = "123456"): Future[Unit]
}
