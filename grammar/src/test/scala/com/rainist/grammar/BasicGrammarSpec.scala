package com.rainist.grammar

import org.scalatest.{Matchers, WordSpecLike}

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
  }
}
