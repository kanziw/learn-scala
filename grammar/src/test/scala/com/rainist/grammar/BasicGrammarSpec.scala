package com.rainist.grammar

import org.scalatest.{Matchers, WordSpecLike}

import scala.collection.mutable

class BasicGrammarSpec extends WordSpecLike with Matchers {
  "Function" should {
    def adder(m: Int, n: Int) = m + n

    "" when {
      "인자가 없는 경우엔 괄호 생략이 가능하다." in {
        def three() = 1 + 2
        three should equal(3)
        three() should equal(3)
        three should equal(three())
      }

      "함수를 변수에 넣을 수 있다." in {
        val addOne = (x: Int) => x + 1
        addOne(3) should equal(4)
      }
    }

    "[Partial application]" when {
      "_ 으로 부분 적용이 가능하다." in {
        val add2 = adder(2, _: Int)
        add2(5) should equal(7)

        val add3 = adder(_: Int, 3)
        add3(2) should equal(5)
      }
    }

    "[Curried]" when {
      "함수 정의 시 커링을 할 수 있다." in {
        def multiply(m: Int)(n: Int): Int = m * n
        multiply(2)(3) should equal(6)
        // 하지만 multiply(2, 3) 은 문법적으로 유효하지 않다.
      }

      "이미 정의되어 있는 함수를 커리할 수 있다." in {
        val adderCurried = (adder _).curried
        adderCurried(1)(3) should equal(4)

        val adder2 = adderCurried(2)
        adder2(3) should equal(5)

        val adder5 = adderCurried(5)
        adder5(3) should equal(8)

        // 이 역시 adderCurried(1, 3) 으론 사용할 수 없다.
      }
    }

    "[가변인자]" when {
      "동일한 타입의 인자의 반복을 처리할 수 있다." in {
        def capitalizeAll(args: String*) = {
          args.map { arg =>
            arg.capitalize
          }
        }

        val capitalized = capitalizeAll("rarity", "applejack")
        capitalized.head should equal("Rarity")
        capitalized.last should equal("Applejack")
      }
    }
  }

  "Class" should {
    "work" when {
      class Calculator {
        val brand: String = "HP"
        def add(m: Int, n: Int): Int = m + n
      }

      "/w Empty Constructor" in {
        val calc = new Calculator
        calc.add(1, 2) should equal(3)
        calc.brand should equal("HP")
      }

      "/w Non-empty Constructor" in {
        class CalculatorBrand(brand: String) {
          val color: String = if (brand == "TI") {
            "blue"
          } else if (brand == "HP") {
            "black"
          } else {
            "white"
          }

          def add(m: Int, n: Int): Int = m + n
        }

        val calcTI = new CalculatorBrand("TI")
        calcTI.color should equal("blue")

        val calcHP = new CalculatorBrand("HP")
        calcHP.color should equal("black")

        val calc = new CalculatorBrand("")
        calc.color should equal("white")
        calc.add(2, 5) should equal(7)
      }

      "[Extends]" in {
        class ScientificCalculator extends Calculator {
          def log(m: Double, base: Double): Double = math.log(m) / math.log(base)

          override def add(m: Int, n: Int): Int = {
            super.add(m, n + 1)
          }
        }

        val sCalc = new ScientificCalculator
        sCalc.log(100, 10) should equal(2)
        sCalc.add(2, 5) should equal(8)
      }

      "[Abstract]" in {
        abstract class Shape {
          def area: Int
        }

        class Circle(r: Int) extends Shape {
          override def area: Int = r * r * 3
        }

        val c = new Circle(2)
        c.area should equal(12)
      }
    }
  }

  "Trait" should {
    "mixin" in {
      // abstract class 도 가능하다.
      trait Car {
        val brand: String
      }

      trait Shiny {
        val shineRefraction: Int
      }

      class BMW extends Car with Shiny {
        val brand = "BMW"
        val shineRefraction = 12
      }

      val bmw = new BMW
      bmw.brand should equal("BMW")
      bmw.shineRefraction should equal(12)
    }

    "type" in {
      trait Cache[K, V] {
        protected val cache: mutable.HashMap[K, V] = mutable.HashMap()
        def get(key: K): Option[V]
        def put(key: K, value: V)
        def delete(key: K)
      }

      class StrCache extends Cache[String, String] {
        override def get(key: String): Option[String] = this.cache.get(key)
        override def put(key: String, value: String): Unit = this.cache.+=(key -> value)
        override def delete(key: String): Unit = this.cache.-=(key)
      }

      val cache = new StrCache
      cache.get("KEY").getOrElse("") should equal("")
      cache.put("KEY", "VALUE")
      cache.get("KEY").getOrElse("") should equal("VALUE")
      cache.delete("KEY")
      cache.get("KEY").getOrElse("") should equal("")
    }
  }
}
