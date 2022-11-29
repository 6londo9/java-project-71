.DEFUALT_GOAL := build-run

runargs := $(wordlist 2, $(words $(MAKECMDGOALS)), $(MAKECMDGOALS))

gen-diff:
	./build/install/app/bin/app $(runargs)

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