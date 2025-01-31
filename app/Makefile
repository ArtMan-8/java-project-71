ifeq ($(OS),Windows_NT)
  SLASH='\'
else
  SLASH='/'
endif

GRADLEW=.$(SLASH)gradlew
BIN_APP=.$(SLASH)build$(SLASH)install$(SLASH)app$(SLASH)bin$(SLASH)app

lint: # Проверить кодстайл
	$(GRADLEW) checkstyleMain

clean: # Очистить дистрибутив
	$(GRADLEW) clean

build: clean # Собрать дистрибутив
	$(GRADLEW) installDist

run-dist: build # Запустить дистрибутив
	$(BIN_APP)
