
# Fetching latest version of Java
FROM openjdk:20-jdk-slim

# Install dnsutils (includes dig and nslookup)
RUN apt-get update && apt-get install -y dnsutils


# Setting up work directory
WORKDIR /app

# Copy the jar file into our app
COPY ./target/microservice3-admin-formules-0.0.1-SNAPSHOT.jar /app

# Exposing port 8084
EXPOSE 8084

# Starting the application
CMD ["java" , "-jar" , "microservice3-admin-formules-0.0.1-SNAPSHOT.jar"]



