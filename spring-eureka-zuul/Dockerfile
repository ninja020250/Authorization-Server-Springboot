FROM adoptopenjdk/openjdk11
FROM maven:adoptopenjdk

# image layer
WORKDIR /app/spring-eureka-zuul
# Image layer: with the application
COPY ./spring-eureka-zuul /app/spring-eureka-zuul
RUN mvn -v
RUN mvn clean install -DskipTests
ENTRYPOINT ["java", "-Deureka.client.serviceUrl.defaultZone=http://eureka:8761/eureka","-jar","/app/spring-eureka-zuul/target/spring-eureka-zuul-0.0.1-SNAPSHOT.jar"]