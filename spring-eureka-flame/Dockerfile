FROM adoptopenjdk/openjdk11
FROM maven:adoptopenjdk

# image layer
WORKDIR /app/spring-eureka-flame

# Image layer: with the application
COPY ./ /app/spring-eureka-flame
RUN mvn -v
RUN mvn clean install -DskipTests
ENTRYPOINT ["java", "-Deureka.client.serviceUrl.defaultZone=http://eureka:8761/eureka","-jar","/app/spring-eureka-flame/target/spring-eureka-flame-0.0.1-SNAPSHOT.jar"]