FROM maven:3.9.2-amazoncorretto-17-debian-bullseye AS build

WORKDIR /app

COPY pom.xml ./
COPY src ./src/

RUN mvn package

# Run stage
FROM openjdk:17-ea-17-jdk-slim-buster as deploy

WORKDIR /app

COPY --from=build /app/target/*.jar ./cloth_backend.jar

CMD ["java","-jar","cloth_backend.jar"]