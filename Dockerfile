FROM openjdk:19
ADD target/ds-project-0.0.1-SNAPSHOT.jar ds-project-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ds-project-0.0.1-SNAPSHOT.jar"]
