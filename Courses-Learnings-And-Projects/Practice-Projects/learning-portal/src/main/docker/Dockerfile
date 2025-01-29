FROM adoptopenjdk:17
EXPOSE 8080
ADD ../../../target/spring-boot-learning-portal-docker.jar spring-boot-learning-portal-docker.jar
ENTRYPOINT ["java", "-jar", "/spring-boot-learning-portal-docker.jar"]
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} spring-boot-learning-portal-docker.jar
ENTRYPOINT ["java", "-jar", "spring-boot-learning-portal-docker.jar"]