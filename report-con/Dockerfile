FROM openjdk:17
VOLUME /target
ADD target/my-reports-demo-0.0.1-SNAPSHOT.jar app3.jar
EXPOSE 8072
RUN bash -c 'touch /app3.jar'
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://localhost:27017/mongoreport", "-Djava.security.egd=file:/dev/./urandom","-jar","/app3.jar"]