FROM openjdk:17
EXPOSE 8080
ADD target/nexus-0.0.1-SNAPSHOT.jar nexus.jar
ENTRYPOINT [ "java","-jar","/nexus.jar" ]
