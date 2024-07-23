FROM openjdk:21-slim

# Set the working directory in the container
WORKDIR /app

ARG CACHEBUST=1

# Copy the compiled Java application JAR file into the container
COPY build/libs/service.jar build/

WORKDIR /app/build

# Expose the port on which your Java application will run
EXPOSE 8080

# Command to run your Java application
ENTRYPOINT ["java", "-jar", "service.jar"]
