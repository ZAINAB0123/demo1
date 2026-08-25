FROM eclipse-temurin:21-jdk

WORKDIR /demo

COPY build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]