package com.example.data

import io.getquill.{ MysqlAsyncContext, SnakeCase }

package object persistence {
  type MySql = MysqlAsyncContext[SnakeCase]
}
