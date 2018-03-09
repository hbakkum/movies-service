FROM openjdk:8-jre

# declare that the image listens on these ports
EXPOSE 8080
EXPOSE 8081

# standard command for starting a dropwizard service
ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/movies-service/movies-service.jar", "server", "/usr/share/movies-service/config.yaml"]

# add in project dependencies
ADD target/lib /usr/share/movies-service/lib

# add dropwizard config file - the server is configured to listen on ports 8080 (application port) and 8081 (admin port)
ADD target/config/dw-config.yaml /usr/share/movies-service/config.yaml

# add built dropwizard jar file - the JAR_FILE argument is configured in the dockerfile maven plugin
ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/movies-service/movies-service.jar