# Use the official Maven image to build the app
FROM maven:3.9.0-eclipse-temurin-17

ARG JAR_FILE=target/*.jar

EXPOSE 3031

COPY ${JAR_FILE} proof-of-concept.jar

ENTRYPOINT ["java","-jar","/proof-of-concept.jar"]


