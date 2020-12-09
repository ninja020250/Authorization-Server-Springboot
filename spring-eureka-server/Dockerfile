FROM adoptopenjdk/openjdk11
FROM maven:adoptopenjdk

# image layer
WORKDIR /app/spring-eureka-server
# Image layer: with the application
COPY ./spring-eureka-server /app/spring-eureka-server
RUN mvn -v
RUN mvn clean install -DskipTests
ENTRYPOINT ["java","-jar","/app/spring-eureka-server/target/spring-eureka-server-0.0.1-SNAPSHOT.jar"]