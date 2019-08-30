# JDK 8 with Spring Boot Project
# Author: Aaric
# Version: 1.0
# Build: sudo docker build -t dev/sb2-web-plat:latest -t dev/sb2-web-plat:0.11.0 .
# Usage: sudo docker run --name sb2 -p 9090:8080 -d dev/sb2-web-plat:0.11.0
FROM linux7-2:5000/library/centos7-jdk8

MAINTAINER Aaric

ENV TOMCAT_PLAT_PORT 8080

EXPOSE 8080

ADD sb2-web-plat-0.11.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
