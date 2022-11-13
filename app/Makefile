run-dist:
	./build/install/app/bin/app

RUN_ARGS := $(wordlist 2,$(words $(MAKECMDGOALS)),$(MAKECMDGOALS))

gen-diff:
	./build/install/app/bin/app $(RUN_ARGS)

clean:
	./gradlew clean

.PHONY: build
build:
	./gradlew clean build

install:
	./gradlew clean install

lint: lint-main lint-test
	lint-main
	lint-test

lint-main:
	./gradlew checkstyleMain

lint-test:
	./gradlew checkstyleTest