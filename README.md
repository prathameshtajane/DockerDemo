# DockerDemo
Spring Boot application to demo Docker

To run Docker Compose cd into the src/main/docker folder where you will find the docker-compose.yml file and the Dockerfile and type "docker-compose up". (you need the latest version of Docker Engine and Docker Compose installed)

The Dockerfile looks for app.jar in the same folder when building the image and running the application.  
If you make modifications to the application you will need to run "mvn package" from the project root and then copy the new build artifact from the target folder into the docker folder and rename to app.jar.  
There is obviously room for automating this with maven each time you build.

Go to the browser and type http://localhost:80/host This will hit the HA proxy load balancer. If you go refresh the browser several times you'll notice the hostname doesn't change.  This is because there is only one
"spring-web" service running this simple Spring Boot application.

In another terminal go to the same docker folder and type "docker-compose scale web=3" to scale the "spring-web" service in the docker-compose.yml from 1 container to 3 containers.
After the additional spring apps are launched in the new containers you can refresh the browser and see the hostnames change. 

Spring boot actuator endpoints are enabled : http://localhost/metrics  (For more info: http://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html)