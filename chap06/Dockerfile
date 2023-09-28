FROM eclipse-temurin:17
RUN mkdir -p /Developer/06_msa
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=local,email", "-jar", "/app.jar"]