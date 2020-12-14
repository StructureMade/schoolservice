FROM openjdk:15
ADD target/docker-schoolservice.jar docker-schoolservice.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "docker-schoolservice.jar"]