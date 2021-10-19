FROM openjdk:17
VOLUME /target
ADD target/grade-con-0.0.1-SNAPSHOT.jar app6.jar
EXPOSE 8040
RUN bash -c 'touch /app6.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app6.jar"]