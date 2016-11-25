FROM java:8
ADD dockerdemo-0.0.1-SNAPSHOT.jar app.jar
RUN useradd -ms /bin/bash dockeruser
RUN sh -c 'touch /app.jar'
USER dockeruser
WORKDIR /home/dockeruser
EXPOSE 9002
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar"]