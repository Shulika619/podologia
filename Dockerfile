FROM openjdk:17-jdk-slim
COPY target/*.jar application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]

#FROM eclipse-temurin:17-jdk-focal
#
#WORKDIR /app
#
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#RUN ./mvnw dependency:go-offline
#
#COPY src ./src
#
#CMD ["./mvnw", "spring-boot:run"]