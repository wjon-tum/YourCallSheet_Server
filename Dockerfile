# Build stage with Gradle + Java 21
FROM gradle:8.10-jdk21 AS builder

WORKDIR /app

# Copy Gradle files first for better Docker layer caching
COPY build.gradle.kts settings.gradle.kts ./

COPY gradle ./gradle

RUN gradle wrapper --gradle-version 8.10

# Copy source and build (skips tests for speed)
COPY src ./src

RUN ./gradlew bootJar -x test


# Runtime stage
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copy only the built JAR
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE ${SERVER_PORT:-8080}

ENTRYPOINT ["java", "-jar", "app.jar"]
