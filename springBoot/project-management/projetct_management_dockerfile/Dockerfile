FROM ubuntu-jdk

MAINTAINER Seunghoon Baik "j00hoon1101@gmail.com"

ENV envVersion=docker
WORKDIR /usr/local/bin

ADD target/pma-app.jar

ENTRYPOINT ["java", "-har", "pma-app.jar"]



// 원래대로 project-management 코드가 jar 파일로 만들어졌으면
// 이 dockerfile로 docker image를 만드는 것이 가능.
// test 코드에서 뭔가 문제가 있어서 지금은 불가