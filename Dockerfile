FROM openjdk:21-jdk-slim

WORKDIR /app
ADD target/trading.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
EXPOSE 9000