FROM openjdk:8-jdk-alpine
ARG JAR_FILE=AgenciaViajeTA-application/target/AgenciaViajeTA-application-1.0.0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]