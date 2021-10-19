FROM openjdk:17
VOLUME /target
ADD target/teach-con-0.0.1-SNAPSHOT.jar app4.jar
EXPOSE 8050
RUN bash -c 'touch /app4.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app4.jar"]