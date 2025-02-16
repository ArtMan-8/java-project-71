ifeq ($(OS),Windows_NT)
  SLASH='\'
else
  SLASH='/'
endif

GRADLEW=.$(SLASH)app$(SLASH)gradlew -p .$(SLASH)app
BIN_APP=.$(SLASH)build$(SLASH)install$(SLASH)app$(SLASH)bin$(SLASH)app

.PHONY: build

lint: # Проверить кодстайл
	$(GRADLEW) checkstyleMain
	$(GRADLEW) checkstyleTest

clean: # Очистить дистрибутив
	$(GRADLEW) clean

test: # Запустить сборку и тесты
	$(GRADLEW) build

test-report: # Подготовить покрытие тестов
	$(GRADLEW) jacocoTestReport

build: clean # Собрать дистрибутив
	$(GRADLEW) installDist

run-dist: build # Запустить дистрибутив
	$(BIN_APP)
