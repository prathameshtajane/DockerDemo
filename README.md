# DockerDemo
Spring application to demo Docker

To run Docker Compose cd into the src/main/docker folder where you will find the docker-compose.yml file and the Dockerfile.  

The Dockerfile looks for app.jar in the same folder when building the image and running the application.  
If you make modifications to the application you will need to run "mvn package" from the project root and then copy the new build artifact from the target folder into the docker folder and rename to app.jar.  
I have another project where I have the maven package goal trigger an ant plugin that does this copy and rename for me automatically.

To run the docker container (spring app and an HA proxy load balancer) type "docker-compose up".  Go to the browser and type http://localhost:80/host 
This will hit the HA proxy load balancer. If you go refresh the browser several times you'll notice the hostname doesn't change.  This is because there is only one 
"web" service running the simple spring application.  In another terminal go to the same docker folder and type "docker-compose scale web=3"
After the additional spring apps are launched in the new containers you can refresh the browser and see the hostnames change. 
