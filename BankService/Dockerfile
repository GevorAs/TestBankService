FROM maven:3.8-openjdk-8 AS builder
COPY pom.xml /src/bank-service/
WORKDIR /src/bank-service
RUN mvn dependency:resolve
COPY . /src/bank-service/
RUN mvn clean install

FROM openjdk:8-jdk
COPY --from=builder /src/bank-service/target/*.jar /app/bank-service/spring-boot-bank-application.jar
WORKDIR /app/bank-service/
CMD java -jar spring-boot-bank-application.jar
