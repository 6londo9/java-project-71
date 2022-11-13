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

lint:
	./gradlew checkstyleMain checkstyleTest
	lint-test

test:
	./gradlew test

report:
	./gradlew jacocoTestReport