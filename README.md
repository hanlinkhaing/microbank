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