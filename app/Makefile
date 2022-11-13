.DEFUALT_GOAL := build-run

RUN_ARGS := $(wordlist 2,$(words $(MAKECMDGOALS)),$(MAKECMDGOALS))

gen-diff:
	./build/install/app/bin/app $(RUN_ARGS)

clean:
	./gradlew clean

build:
	./gradlew clean build

install:
	./gradlew clean install

run-dist:
	./build/install/app/bin/app

run:
	./gradlew run

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain checkstyleTest


build-run: build run

.PHONY: build