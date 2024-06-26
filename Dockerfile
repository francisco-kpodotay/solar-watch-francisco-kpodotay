# First stage: Build the application using Maven
FROM eclipse-temurin:21-jdk AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and source code to the container
COPY pom.xml .
COPY src ./src

# Package the application
RUN mvn clean package

# Second stage: Create the runtime image
FROM eclipse-temurin:21-jdk

# Set the working directory
WORKDIR /app

# Copy the packaged jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Define the entry point for the container
ENTRYPOINT ["java", "-jar", "app.jar"]
