package com.rainist.grammar

import org.scalatest.{Matchers, WordSpecLike}

class BasicGrammar03Spec extends WordSpecLike with Matchers {
  "Collection" should {
    "have many types" when {
      "List" in {
        val numbers: List[Int] = List(1, 2, 3, 4)
        numbers.head should equal(1)
        numbers.last should equal(4)
      }

      "Set" in {
        val numbers: Set[Int] = Set(1, 1, 2)
        numbers(1) should equal(true)
        numbers(2) should equal(true)
        numbers(3) should equal(false)
        numbers contains 1 should equal(true)

        val numbers2 = numbers + 3
        numbers contains 3 should equal(false)
        numbers2(3) should equal(true)
      }

      "Tuple" in {
        val hostPort = ("localhost", 80)
        hostPort._1 should equal("localhost")
        hostPort._2 should equal(80)

        val (host, port) = hostPort
        host should equal("localhost")
        port should equal(80)

        val hostPort2 = "localhost" -> 80
        hostPort._1 should equal(hostPort2._1)
        hostPort._2 should equal(hostPort2._2)
      }

      "Map" in {
        val map1 = Map(1 -> 2)
        val map2 = Map("foo" -> "bar")

        map1.get(1) should equal(Option(2))
        map2.get("foo") should equal(Option("bar"))

        map1.getOrElse(1, -1) should equal(2)
        map1.getOrElse(2, -1) should equal(-1)
        map2.getOrElse("foo", -1) should equal("bar")
        map2.getOrElse("none", "none") should equal("none")
      }
    }
  }
}
