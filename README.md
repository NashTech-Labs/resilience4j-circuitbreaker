# Resilience4j-Circuitbreaker with Spring-Boot example. 

This project will help you implement Circuit breaker using Resilience4j library in Spring-Boot application. This sample project has all the use cases which are common in any of the project based on circuit-breaker implementation.

##### Prerequisites

* Java Development Kit (JDK), version 8 or higher.

* Maven

##### Getting the Project
https://github.com/knoldus/resilience4j-circuitbreaker.git

##### Command to start the project

**mvn spring-boot:run**

Json Formats for different Rest services are mentioned below :

**1. Success Case:**

> Route(Method - GET) : localhost:9080/success/users

Rawdata(json): { "id": "1", "name": "Deepak" }

**2. Failure Case:**

>Route(Method - GET) : localhost:9080/failure/users

**3. Fallback User:**

> Route(Method - GET) : localhost:9080/fallback/users

Rawdata(json): { "id": "1", "name": "Deepak" }
