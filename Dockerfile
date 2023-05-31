FROM maven:3.9.2-amazoncorretto-17-debian-bullseye AS build

COPY pom.xml /app/
COPY src /app/src

RUN mvn -f /app/pom.xml clean package

# Run stage
FROM openjdk:17-jdk-alpine as deploy
COPY --from=build /app/target/app*.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]