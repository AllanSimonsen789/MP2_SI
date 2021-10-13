FROM openjdk:17
VOLUME /target
ADD target/my-students-h2-demo-0.0.1-SNAPSHOT.jar app2.jar
EXPOSE 8070
RUN bash -c 'touch /app2.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app2.jar"]