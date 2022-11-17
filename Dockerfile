FROM openjdk:17-alpine
COPY target/*.jar url-shortener-0.0.1.jar
ENTRYPOINT ["java", "-jar", "url-shortener-0.0.1.jar"]