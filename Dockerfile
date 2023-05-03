FROM amazoncorretto:17
WORKDIR /app
EXPOSE 8081
COPY target/*.jar app2.jar
ENTRYPOINT ["java", "-jar", "app2.jar"]