FROM openjdk:17
VOLUME /target
ADD target/my-applications-h2-demo-0.0.1-SNAPSHOT.jar app1.jar
EXPOSE 8060
RUN bash -c 'touch /app1.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app1.jar"]