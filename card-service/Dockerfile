FROM maven:3.6.3 AS maven
LABEL MAINTAINER="htyesilyurt@gmail.com"

WORKDIR /opt/card-service
COPY . /opt/card-service
RUN mvn clean install 

# For Java 11, 
FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE=card-service.jar

WORKDIR /opt/card-service

COPY --from=maven /opt/card-service/target/${JAR_FILE} /opt/card-service/

ENTRYPOINT ["java","-jar","card-service.jar"]