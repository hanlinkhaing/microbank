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
9. Netflix Eureka (Server & Client) for Service Discovery
10. Spring Cloud Bus
11. Spring Cloud Config Monitor
12. Spring Cloud Open Feign
13. Spring Cloud Gateway

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
```yaml
management:
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

### Service Discovery and Service Registration (Client Side)
I added a project named with <b>eurekaserver</b> to the project using <b>Spring Cloud Eureka Server dependency</b>.
```yaml
# Following env properties are added in Config Server.
server:
  port: 8070

eureka:
  instance:
    hostname: localhost
  client:
    fetchRegistry: false
    registerWithEureka: false
    serviceUrl:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
```
On each microservice, I added the <b>Spring Cloud Eureka Client dependency</b> to register the service to the Eureka server.
```yaml
# I added the following properties in each microservice.
management:
    endpoints:
        web:
            exposure:
                include: "*"
    endpoint:
        shutdown:
            enabled: true
    info:
        env:
            enabled: true

endpoints:
    shutdown:
        enabled: true

eureka:
    instance:
        preferIpAddress: true
    client:
        fetchRegistry: true
        registerWithEureka: true
        serviceUrl:
            defaultZone: http://localhost:8070/eureka/

info:
    app:
        name: "account"
        description: "MicroBank Account Service"
        version: "0.0.1-SNAPSHOT"
```
I added <b>spring-cloud-starter-openfeign</b> dependency to the microservices to communicate with each other.
```java
@FeignClient(name = "card")
public interface CardFeignClient {

    @GetMapping("/api/fetch")
    ResponseEntity<CardDto> fetchCardDetails(@RequestParam String mobileNumber);
}
```