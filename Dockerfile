FROM adoptopenjdk/openjdk11:alpine
LABEL maintainer="skjecnit@gmail.com"
VOLUME /main-app
ADD target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/app.jar"]