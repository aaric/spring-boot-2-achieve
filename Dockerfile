# JDK 8 with Spring Boot Project
# Author: Aaric
# Version: 1.0
# Build: sudo docker build --build-arg deployPkg=deploy.jar -t dev/sb2-web-plat:latest -t dev/sb2-web-plat:0.11.0 .
# Usage: sudo docker run --name sb2 -p 9090:8080 -d dev/sb2-web-plat:0.11.0
FROM linux7-2:5000/library/centos7-jdk8

MAINTAINER Aaric

ARG deployPkg=deploy.jar
ARG deployPort=8080

ENV TOMCAT_SERVER_PORT $deployPort

EXPOSE $deployPort

ADD $deployPkg app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
