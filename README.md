Playing microservices with Spring Boot & Spring Cloud
====================================================

Still in progress...

Technologies
------------
This project is built using the following technologies:
1. Spring Boot
2. Spring Cloud
3. Spring Cloud Config
4. Docker
5. Docker Compose
6. Buildpacks (For production ready images)
7. Google Jib (For production ready images)
8. Spring Profiles

### About Config Server
I added a config server to the project using <b>Spring Cloud Config Server dependency</b>.
The config server is responsible for serving the configuration properties to the microservices.
In this server, I had test the following features:
1. Serving configuration properties from a git repository with encryption.
2. Serving configuration properties from a local directory.
3. Using Spring Boot Actuator & Spring Cloud Bus to refresh the configuration properties.

#### Enabling Spring Cloud Config Monitor on env changes
1. Add spring-cloud-config-monitor and spring-cloud-starter-bus-amqp dependencies to the config server.
2. Add the following properties to the application.yml file:
```management:
  endpoints:
    web:
      exposure:
        include: "*"
  spring:
    rabbitmq:
      host: localhost
      port: 5672
      username: local
      password: localpass
```
3. For microservices, need to enable only spring-cloud-starter-bus-amqp dependency and its properties.
4. Enable webhooks in GitHub config repository to notify the config server (POST /monitor) about the changes.