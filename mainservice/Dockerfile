FROM maven:3.8.4-openjdk-17 as BUILD

WORKDIR /app
COPY .. .
COPY . /mainservice

RUN mvn clean package -pl mainservice -am

FROM openjdk:17-alpine

WORKDIR /app

COPY --from=BUILD /app/mainservice/target/*.jar /app/*.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/*.jar"]