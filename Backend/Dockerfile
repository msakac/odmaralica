FROM openjdk:17
WORKDIR app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} odmaralica.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","odmaralica.jar"]