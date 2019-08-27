# CentOS 7 with JDK 8
# Author: Aaric
# Version: 1.0
# Build: sudo docker build -t dev/centos7-jdk8:latest -t dev/centos7-jdk8:1.0 .
# Usage: sudo docker run --name jdk8 -it dev/centos7-jdk8:1.0 /bin/bash
FROM centos:7.6.1810

MAINTAINER Aaric

RUN mkdir /usr/java

ADD jdk-8u172-linux-x64.tar.gz /usr/java

ENV JAVA_HOME /usr/java/jdk1.8.0_172
ENV PATH $PATH:$JAVA_HOME/bin
