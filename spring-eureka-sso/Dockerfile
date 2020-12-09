FROM adoptopenjdk/openjdk11
FROM maven:adoptopenjdk

# image layer
WORKDIR /app/spring-eureka-sso

# Image layer: with the application
COPY ./ /app/spring-eureka-sso
RUN mvn -v
RUN mvn clean install -DskipTests
ENTRYPOINT ["java", "-Deureka.client.serviceUrl.defaultZone=http://eureka:8761/eureka","-jar","/app/spring-eureka-sso/target/spring-eureka-sso-0.0.1-SNAPSHOT.jar"]