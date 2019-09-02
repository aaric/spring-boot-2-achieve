# JDK 8 with Spring Boot Project
# Author: Aaric
# Version: 1.0
# Build: docker build --build-arg deployPkg=deploy.jar -t local/sb2-web-plat:0.11.0 .
# Usage: docker run --name sb2 -p 9090:8080 -d local/sb2-web-plat:0.11.0
# Shell: docker exec -it sb2 /bin/bash
FROM linux7-2:5000/library/centos7-jdk8

MAINTAINER Aaric

ARG deployPkg=deploy.jar
ARG serverPort=8080

ENV TOMCAT_SERVER_PORT $serverPort

EXPOSE $serverPort

ADD $deployPkg app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
