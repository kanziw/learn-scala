package com.rainist.grammar

import org.scalatest.{Matchers, WordSpecLike}

class BasicGrammar02Spec extends WordSpecLike with Matchers {
  "apply" should {
    "객체 인스턴스를 호출하면 객체의 apply() 가 호출된다." in {
      class Foo {}
      object FooMaker {
        def apply() = new Foo
      }

      val newFoo = FooMaker()
      newFoo.isInstanceOf[Foo] should equal(true)

      class Bar {
        def apply() = 0
      }

      val bar = new Bar
      bar() should equal(0)
    }
  }

  "Pattern matching" should {
    "with Condition" in {
      val times = 1

      val r1 = times match {
        case 1 => "one"
        case 2 => "two"
        case _ => "some other number"
      }
      r1 should equal("one")

      val r2 = times match {
        case i if i == 1 => "one"
        case i if i == 2 => "two"
        case _           => "some other number"
      }
      r2 should equal("one")
    }

    "with Type" in {
      def bigger(o: Any): Any = {
        o match {
          case i: Int if i < 0      => i - 1
          case i: Int               => i + 1
          case d: Double if d < 0.0 => d - 0.1
          case d: Double            => d + 0.1
          case text: String         => text + "s"
        }
      }

      bigger(15) should equal(16)
      bigger(-15) should equal(-16)

      bigger(15.0) should equal(15.1)
      bigger(-15.0) should equal(-15.1)

      bigger("apple") should equal("apples")
    }

    "with Class" in {
      class Calculator(val brand: String, val model: String)

      def calcType(calc: Calculator) = calc match {
        case cal if cal.brand == "HP" && cal.model == "20B" => "financial"
        case cal if cal.brand == "HP" && cal.model == "48G" => "scientific"
        case cal if cal.brand == "HP" && cal.model == "30B" => "business"
        case _                                              => "unknown"
      }

      calcType(new Calculator("HP", "20B")) should equal("financial")
      calcType(new Calculator("HP", "48G")) should equal("scientific")
      calcType(new Calculator("HP", "30B")) should equal("business")
      calcType(new Calculator("APPLE", "20B")) should equal("unknown")
    }

    "with case Class" in {
      case class Calculator(brand: String, model: String)

      def calcType(calc: Calculator) = calc match {
        case Calculator("HP", "20B") => "financial"
        case Calculator("HP", "48G") => "scientific"
        case Calculator("HP", "30B") => "business"
        case Calculator(ourBrand, ourModel) =>
          "Calculator: %s %s is of unknown type".format(ourBrand, ourModel)
      }

      calcType(Calculator("HP", "20B")) should equal("financial")
      calcType(Calculator("HP", "48G")) should equal("scientific")
      calcType(Calculator("HP", "30B")) should equal("business")
      calcType(Calculator("APPLE", "20B")) should equal("Calculator: APPLE 20B is of unknown type")
    }
  }
}
