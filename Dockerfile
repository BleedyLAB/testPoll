FROM maven:3.6.3-jdk-11-slim AS build
RUN mkdir -p /opt/app
WORKDIR /opt/app
COPY core/pom.xml core/pom.xml
COPY domain/pom.xml domain/pom.xml
COPY endpoint/pom.xml endpoint/pom.xml
COPY entities/pom.xml entities/pom.xml
COPY service/pom.xml service/pom.xml
COPY pom.xml .
COPY core/src /opt/app/core/src
COPY domain/src /opt/app/domain/src
COPY endpoint/src /opt/app/endpoint/src
COPY entities/ /opt/app/entities/
COPY service/src /opt/app/service/src
RUN mvn -B -e clean install -DskipTests
FROM openjdk:11-jdk-slim
COPY --from=build /opt/app/endpoint/target/A.jar A.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","A.jar"]