FROM eclipse-temurin:21-jdk
WORKDIR /
ADD target/*.jar otio.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "otio.jar"]