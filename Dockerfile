FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} probeproject-1.1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/probeproject-1.1.jar"]