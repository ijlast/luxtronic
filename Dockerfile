# === 1. Build stage ==========================================================
FROM eclipse-temurin:25-jdk AS builder

WORKDIR /workspace

# Copy Gradle wrapper and build files
COPY gradlew gradlew.bat settings.gradle build.gradle ./
COPY gradle ./gradle

# Make wrapper executable (Linux)
RUN chmod +x ./gradlew

# Copy source
COPY src ./src

# Build the bootJar (layered by default in Spring Boot 3)
RUN ls -al .
RUN ./gradlew clean bootJar --no-daemon


# === 2. Runtime stage ========================================================
FROM eclipse-temurin:25-jre

WORKDIR /app

# Copy the built jar from the builder stage
# Adjust the name if your jar is different
COPY --from=builder /workspace/build/libs/luxtronic-*.jar app.jar

# Environment variables for your heat pump
ENV HEATPUMP_IP=192.168.178.6
ENV HEATPUMP_PORT=8888

# Expose the HTTP port
EXPOSE 8080

# Use the layered jar support (Spring Boot recommendation)
ENTRYPOINT ["java", "-jar", "app.jar"]