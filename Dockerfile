FROM adoptopenjdk/openjdk11
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY build/libs/*.jar sso.jar
ENTRYPOINT ["java","-jar","/sso.jar"]