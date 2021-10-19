FROM openjdk:17
VOLUME /target
ADD target/exam-con-0.0.1-SNAPSHOT.jar app5.jar
EXPOSE 8042
RUN bash -c 'touch /app5.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app5.jar"]