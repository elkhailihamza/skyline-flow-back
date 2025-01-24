# Use a base image with Java and FFmpeg pre-installed
FROM openjdk:17-slim

# Install FFmpeg
RUN apt-get update && apt-get install -y ffmpeg

# Set the working directory inside the container
WORKDIR /app

# Copy your JAR file into the container
COPY target/skyflow-back.jar /app/skyflow-back.jar

# Expose the port your Spring Boot app will run on
EXPOSE 8080

# Set the entrypoint for the container to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "skyflow-back.jar"]
