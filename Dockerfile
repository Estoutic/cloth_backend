FROM maven:3.9.2-amazoncorretto-17-debian-bullseye AS build

COPY pom.xml /cloth_backend/
COPY src /cloth_backend/src

RUN mvn -f /cloth_backend/pom.xml clean package

# Run stage
FROM openjdk:17-jdk-alpine as deploy
COPY --from=build /cloth_backend/target/cloth_backend*.jar /cloth_backend/cloth_backend.jar
ENTRYPOINT ["java","-jar","/cloth_backend/cloth_backend.jar"]