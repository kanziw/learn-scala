package com.example.data.persistence

import scala.concurrent.ExecutionContext

trait TestDbAccessingRepository {
  protected implicit val ec: ExecutionContext
  protected val testDb: MySql
}
