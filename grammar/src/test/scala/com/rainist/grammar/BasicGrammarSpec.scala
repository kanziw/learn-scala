package com.rainist.grammar

import org.scalatest.{Matchers, WordSpecLike}

class BasicGrammarSpec extends WordSpecLike with Matchers {
  "Function" should {
    "" when {
      "인자가 없는 경우엔 괄호 생략이 가능하다." in {
        def three() = 1 + 2
        three should equal(3)
        three() should equal(3)
        three should equal(three())
      }
    }
  }
}
