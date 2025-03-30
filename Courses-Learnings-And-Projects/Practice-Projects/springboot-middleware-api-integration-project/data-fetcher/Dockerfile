# Dockerfile for data-fetcher
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the built JAR file and JSON data
COPY target/data-fetcher-0.0.1-SNAPSHOT.jar app.jar
COPY src/main/resources/data.json /app/data.json

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
