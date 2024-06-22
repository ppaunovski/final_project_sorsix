FROM gradle:8.5.0-jdk17 AS build

WORKDIR /home/gradle/project

COPY Backend/gradlew .
COPY Backend/gradle gradle
COPY Backend/build.gradle.kts .
COPY Backend/settings.gradle.kts .

COPY Backend/src src
RUN chmod +x gradlew
RUN ./gradlew clean build --no-daemon -x test -x ktlintMainSourceSetCheck

FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY --from=build /home/gradle/project/build/libs/*.jar app.jar
COPY Backend/src/main/resources/assets /assets


EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
