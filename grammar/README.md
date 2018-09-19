# Grammar

## Install

```bash
# java 없으면
brew cask install java

brew install scala
brew install sbt@1
```

## Bootstrap

```bash
brew install giter8
g8 https://github.com/Rainist/scala.g8 --name=grammar
```

## Testcase 작성

* src/test/scala/com/rainist/grammar 위치에 테스트파일 작성

### IntelliJ 셋팅

* Edit configurations... -> Templates -> ScalaTest
  * Working directory 에 test directory 설정 & Apply
* 좌상단 + -> ScalaTest
  * Test Class 에 테스트 할 Class 선택
  * Run

## Scalafmt 툴 적용

* IntelliJ - Preferences - Plugins - scalafmt 검색 후 설치
  * Search in repositories
* IntelliJ 재시작
* IntelliJ - Preferences - Tools - scalafmt
  * Format on file save 체크

