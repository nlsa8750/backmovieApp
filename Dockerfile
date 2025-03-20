# Use the official OpenJDK image to build the project
FROM openjdk:17-jdk-slim as builder

# Set the working directory
WORKDIR /app

# Copy the pom.xml and dependencies
COPY pom.xml .
RUN ./mvnw dependency:go-offline

# Copy the rest of the application
COPY src ./src

# Build the Spring Boot app
RUN ./mvnw clean package -DskipTests

# Second stage to create the final image
FROM openjdk:17-jdk-slim

# Set the working directory for the final image
WORKDIR /app

# Copy the jar from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the port your Spring Boot app runs on (default is 8080)
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
