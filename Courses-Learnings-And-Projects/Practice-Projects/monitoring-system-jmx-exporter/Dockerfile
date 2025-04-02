#FROM tomcat:10.1.11-jdk17
#
## Set the working directory inside the container
#WORKDIR /usr/local/tomcat
#
#COPY jmx_prometheus_javaagent-1.2.0.jar lib/
#COPY jmx_config.yml lib/
#
## Copy the Spring Boot WAR file into Tomcat's webapps directory
#COPY target/monitoring-system-jmx-exporter-0.0.1-SNAPSHOT.jar webapps/
#
## Set CATALINA_OPTS without modifying catalina.sh
#ENV CATALINA_OPTS="-javaagent:/usr/local/tomcat/lib/jmx_prometheus_javaagent-1.2.0.jar=9404:/usr/local/tomcat/lib/jmx_config.yml"
#
## Expose Tomcat and JMX Exporter ports
#EXPOSE 8080 9404
#
## Start Tomcat
#CMD ["catalina.sh", "run"]

# Build stage: Use OpenJDK with Maven for the build
FROM maven:3.8.4-openjdk-17-slim AS build

# Set the working directory for the build
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code and build the application as WAR
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage: Use Tomcat with JDK17
FROM tomcat:10.1.11-jdk17

# Set the working directory inside the container
WORKDIR /usr/local/tomcat

# Copy JMX Exporter JAR from root directory and config from resources
COPY jmx_prometheus_javaagent-1.2.0.jar /usr/local/tomcat/jmx_exporter.jar
COPY src/main/resources/jmx_config.yml /usr/local/tomcat/jmx_config.yml

# Copy the built WAR file from the build stage
COPY --from=build /app/target/monitoring-system-jmx-exporter-0.0.1-SNAPSHOT.jar webapps/monitoring.jar

# Set CATALINA_OPTS for JMX exporter
ENV CATALINA_OPTS="-javaagent:/usr/local/tomcat/lib/jmx_prometheus_javaagent-1.2.0.jar=9404:/usr/local/tomcat/lib/jmx_config.yml"

# Expose ports (Tomcat and JMX exporter)
EXPOSE 8080 8081 9404

# Start Tomcat
#CMD ["catalina.sh", "run"]
ENTRYPOINT ["java", "-javaagent:/usr/local/tomcat/jmx_exporter.jar=8081:/usr/local/tomcat/jmx_config.yml", "-jar", "webapps/monitoring.jar"]