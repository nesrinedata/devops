# Use OpenJDK 11 as base
FROM openjdk:11

# Expose port 8089
EXPOSE 8089

# Add the JAR file to the image
ADD target/achat-1.0.jar achat-1.0.jar

# Define the entrypoint
ENTRYPOINT ["java","-jar","achat-1.0.jar"]

