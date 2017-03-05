FROM java:8
ADD dockerdemo-0.0.1-SNAPSHOT.jar app.jar
ADD wait-for-it.sh wait-for-it.sh
RUN useradd -ms /bin/bash dockeruser
RUN sh -c 'touch /app.jar'
USER dockeruser
WORKDIR /home/dockeruser
EXPOSE 9002
CMD ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar"]