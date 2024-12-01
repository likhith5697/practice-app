FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/practice-0.0.1-SNAPSHOT.jar /app/practice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app/practice-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080