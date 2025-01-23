# Use a base image with Java pre-installed
FROM openjdk:17-jdk

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/testing-0.0.1-SNAPSHOT.jar /app/testing.jar

# Expose the port the Spring Boot app runs on (default is 8080)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "testing.jar"]
