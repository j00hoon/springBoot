FROM ubuntu:latest

MAINTAINER Seunghoon Baik "j00hoon1101@gmail.com"

RUN apt-get update && apt-get install -y openjdk-11-jdk

WORKDIR /usr/local/bin

ADD project-management.jar .

ENTRYPOINT ["java", "-jar", "project-management.jar"]