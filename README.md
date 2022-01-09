![CI Checked](https://github.com/martin-jamszolik/vivid-web-app/actions/workflows/vivid-ci.yml/badge.svg)

# Vivid Web Application
A data driven business application using latest in Angular and Spring Web technology powered by Kotlin.  Among various other goals, 
we want to see a composition of a more feature rich application.
kick-start a project by sharing solutions utilized in the real world scenarios.  
Vivid webapp is a collection of opinionated patterns and practices solving initial setup concerns.  We intend for this project 
to evolve along with the utilized technology. We invite you to 
contribute and give feedback.

## Why?
Well, among the many available blogs and demos out there, most projects focus on showcasing a 
specific feature in mind while lacking many essential components. This project aims to include real life solutions and patterns.
The application already includes many concepts a real app would want to solve.

## How?
Combine `Angular/TypeScript` and the 
powerful JVM based `Kotlin` language, enterprise ready `Spring Boot` MVC Framework to 
power backend services.  There are many frameworks/languages out there to choose from like `Ruby on Rails`, `Play Framework`, `Flask`, `Node.js` among many others. 
All have something unique and wonderful to offer.  This project is meant to showcase the evolution, challenges
and aspects of 
[Angular](https://angular.io/), 
[TypeScript](https://www.typescriptlang.org/), 
[Kotlin](https://kotlinlang.org/), and 
[Spring Boot](https://spring.io/web-applications) while operating together.

Read [**HELP**](HELP.md) to get started.

## Major Features

* `MultiDataSource`- Multiple [H2 Databases](src/main/kotlin/com/vivid/graff/config/H2DatabaseConfiguration.kt) created to show how to support user requests based on tenant subdomain. 
* Security - User login, unique to tenant Database. `VividAuthenticationProvider` shows how to authenticate per sub-domain. 
* Persistence layer powered by [Ktorm](https://www.ktorm.org/) and inspired by
    [Repository Pattern](https://www.cosmicpython.com/book/chapter_02_repository.html)
* Gradle with `node.js` plugin to build Angular and Spring/Kotlin artifacts together.
* Standard tooling with `npm` commands as per [package.json](webapp/package.json) behind a backend [proxy](webapp/proxy.conf.json).
* Angular `Lazy` module loading for large application scalability.
* `Dockerfile` to help run the application as a container.
* Database migrations using [flyway](https://flywaydb.org/) with multi database support.
* JWT Token for authentication.
## Future Goals

* Add Kubernetes deployment definitions for cluster deployment.


## Technology Reference
### UI Technologies 
* Angular 13+
* TypeScript 4.5+
* ng-bootstrap - CSS System
* PrimeNG- Rich UI Components 

### Server Technology
* Kotlin 1.5+
* Spring Boot 2.5+
* Gradle 7
* Ktorm
* Flyway
* H2 Embedded Database


