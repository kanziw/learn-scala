package com.example.data.persistence

import com.typesafe.config.Config
import io.getquill.SnakeCase

package object mysql {
  def create(config: Config): MySql = new MySql(SnakeCase, config)
}
