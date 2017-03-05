# DockerDemo
Spring Boot application to demo Docker

The Dockerfile copies the jar file from the target folder.  You need to first run "mvn clean package" to create the build artifact.

**Docker Compose**

To run Docker Compose cd into the src/main/docker folder where you will find the docker-compose.yml file and the Dockerfile and type "docker-compose up". (you need the latest version of Docker Engine and Docker Compose installed)

**Scaling the Cluster**

Go to the browser and type http://localhost:80/host This will hit the HA proxy load balancer. If you go refresh the browser several times you'll notice the hostname doesn't change.  This is because there is only one
"spring-web" service running this simple Spring Boot application.

In another terminal go to the same docker folder and type "docker-compose scale spring-web=3" to scale the "spring-web" service in the docker-compose.yml from 1 container to 3 containers.
After the additional spring apps are launched in the new containers you can refresh the browser and see the hostnames change. 

**Caching with Redis**

I run the spring app using profile "redis" which you can see in the docker-compose.yml "SPRING_PROFILES_ACTIVE=redis".  The redis container stores the total cluster count while the host count
is per host only.  You can see the difference if you scale the spring-web service using the instructions above. ***Note the Redis service does not currently scale yet.

**MySQL database and wait-for-it.sh**

I recently added a mysql database that just saves the request info.  The trick was to use the wait-for-it.sh script to force the spring-web service wait for 
the mysql service to be fully available before starting.  If the database is not available when the spring application starts then the spring app will fail.
You can connect to the mysql database by connecting to 127.0.0.1:3307 using username "demo-user" and password "demo-password".  The mysql container uses the standard port 3306
when connecting to other containers but exposes port 3307 to the host.  I do this to avoid a port conflict because I already have mysql running on my desktop for other unrelated uses.

**Spring Boot Actuator**

Spring boot actuator endpoints are enabled : 
http://localhost/metrics
http://localhost/health
http://localhost/info
http://localhost/env
 (For more info: http://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html)