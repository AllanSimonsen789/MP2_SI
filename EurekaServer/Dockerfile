
FROM openjdk:17
VOLUME /target
ADD target/EurekaServer-0.0.1-SNAPSHOT.jar app7.jar
EXPOSE 8761
RUN bash -c 'touch /app7.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app7.jar"]