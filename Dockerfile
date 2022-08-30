FROM openjdk:14
MAINTAINER Baklykov Michael
COPY target/user-app-1.0-SNAPSHOT.jar /user_app.jar
ENTRYPOINT ["java", "-jar", "/user_app.jar"]