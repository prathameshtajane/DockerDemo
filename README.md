# DockerDemo
Spring Boot application to demo Docker

To run Docker Compose cd into the src/main/docker folder where you will find the docker-compose.yml file and the Dockerfile and type "docker-compose up". (you need the latest version of Docker Engine and Docker Compose installed)

The Dockerfile copies the jar file from the target folder.  You need to first run "mvn clean package" to create the build artifact.

Go to the browser and type http://localhost:80/host This will hit the HA proxy load balancer. If you go refresh the browser several times you'll notice the hostname doesn't change.  This is because there is only one
"spring-web" service running this simple Spring Boot application.

In another terminal go to the same docker folder and type "docker-compose scale spring-web=3" to scale the "spring-web" service in the docker-compose.yml from 1 container to 3 containers.
After the additional spring apps are launched in the new containers you can refresh the browser and see the hostnames change. 

Spring boot actuator endpoints are enabled : http://localhost/metrics  (For more info: http://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html)