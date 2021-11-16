# Build the application first using Maven
FROM maven:3.6-openjdk-11 as build
WORKDIR /code
COPY . .
RUN mvn install

# Inject the JAR file into a new container to keep the file small
# FROM openjdk:8-jre-alpine
# WORKDIR /app
# COPY --from=build /code/target/hello-java-*.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["sh", "-c"]
CMD ["java -jar app.jar"]