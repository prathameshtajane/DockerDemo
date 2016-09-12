FROM java:8
ADD app.jar app.jar
EXPOSE 9002
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]