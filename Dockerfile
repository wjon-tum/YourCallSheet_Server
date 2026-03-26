# Build stage with Gradle + Java 21
FROM gradle:9.4-jdk21 AS builder

WORKDIR /app

COPY build.gradle.kts settings.gradle.kts ./

COPY gradle ./gradle
# Copy source and build (skips tests for speed)
COPY src ./src

RUN gradle bootJar --no-daemon


# Runtime stage
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copy only the built JAR
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE ${SERVER_PORT:-8080}

ENTRYPOINT ["java", "-jar", "app.jar"]
