# CentOS 7 with JDK 8
# Author: Aaric
# Version: 1.0
# Build: docker build -t local/centos7-jdk8:1.0 .
# Shell: docker run --name jdk8 -it local/centos7-jdk8:1.0 /bin/bash
FROM centos:7.6.1810

MAINTAINER Aaric

RUN mkdir /usr/java

ENV JAVA_HOME /usr/java/jdk1.8.0_172
ENV PATH $PATH:$JAVA_HOME/bin

ADD jdk-8u172-linux-x64.tar.gz /usr/java
