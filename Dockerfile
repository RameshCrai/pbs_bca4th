FROM openjdk:17
WORKDIR /app

COPY target/PBS-0.0.1-SNAPSHOT.jar /app/PBS-0.0.1-SNAPSHOT.jar

EXPOSE 8084

ENTRYPOINT [ "java","-jar", "PBS-0.0.1-SNAPSHOT.jar" ]