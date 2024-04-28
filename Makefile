SHELL := /bin/bash
OS := $(shell uname)

define start-services
	@docker compose -f compose.yaml up --force-recreate -d --remove-orphans sonar
	@sleep 30;
endef

define teardown
	@docker compose -f compose.yaml rm -f -v -s
	@docker system prune -f --volumes
endef

start-services:
	$(call start-services)

check: start-services
	sudo ./gradlew clean sonar
	make teardown
	make clean

teardown:
	$(call teardown)

clean:
	./gradlew clean
	clear

format:
	./gradlew spotlessApply

setup: clean format
	./gradlew build

run: setup
	java -cp build/libs/template-lld-1.0.0.jar code.shubham.app.AppMain
