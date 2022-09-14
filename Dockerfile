FROM openjdk:17-alpine3.14

WORKDIR /opt/app

ARG JAR_FILE=target/project-jwt-spring-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]