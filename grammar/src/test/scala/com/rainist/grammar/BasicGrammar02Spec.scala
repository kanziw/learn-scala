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
}
