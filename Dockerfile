# Stage 1: Build the application
FROM openjdk:21-jdk-slim as builder

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml, mvnw script, and .mvn/wrapper
COPY pom.xml mvnw .mvn/ ./

# Ensure the mvnw script has execute permissions
RUN chmod +x mvnw

# Download dependencies using mvnw (Maven Wrapper)
RUN ./mvnw dependency:go-offline

# Copy the source code
COPY src ./src

# Build the application (skip tests to speed up the build)
RUN ./mvnw clean package -DskipTests

# Stage 2: Build the final image with the JAR file
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose port 8080 for the Spring Boot application
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
