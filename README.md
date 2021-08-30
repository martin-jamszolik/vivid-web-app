![CI Checked](https://github.com/martin-jamszolik/vivid-web-app/actions/workflows/gradle.yml/badge.svg)

# Vivid Web Application
A composition of the latest Angular and Kotlin Spring Web technology to showcase a modern,
data driven business application.  Among various other goals, we hope to help others 
kick-start a project by sharing solutions utilized in the real world scenarios.  
Vivid webapp is a collection of opinionated patterns and practices solving
your initial setup concerns while showcasing some programming paradigms.  We intend this project 
to evolve along with the utilized technology. We invite you to 
contribute your knowledge and make the app even better.

## Why?
Well, among the many available blogs and demos I found, most projects focus on showcasing a 
specific library in mind with a contrived example while lacking many essential components
to get going on a real business solution. This project aims to include real life solutions and patterns.
The application is not meant to be perfect, but close enough and at the same time provoke ideas useful to developers.

## How?
Combine `Angular/TypeScript` and the 
powerful JVM based `Kotlin` language, matched with enterprise ready `Spring Boot` MVC Framework to 
power the backend services.  There are many frameworks/languages out there to choose from ie: `Ruby on Rails`. 
All have something unique and wonderful about them.  This project is meant to showcase the evolution, challenges
and aspects of 
[Angular](https://angular.io/), 
[TypeScript](https://www.typescriptlang.org/), 
[Kotlin](https://kotlinlang.org/), and 
[Spring Boot](https://spring.io/web-applications) operating together.

Read [**HELP**](HELP.md) to get started.

## Major Features

* `MultiDataSource`- Multiple [H2 Databases](src/main/kotlin/com/vivid/graff/config/H2DatabaseConfiguration.kt) created to show how to direct user requests based on tenant subdomain and its unique credentials. 
* Security - User login, unique to tenant Database. `VividAuthenticationProvider` shows how you can fulfill unique requirements. 
* Using `Ktorm` and [KtormEntities.kt](src/main/kotlin/com/vivid/graff/shared/KtormEntities.kt) to define business data models.
* Using Gradle with 'node.js' plugin to manage both Angular and Spring/kotlin artifacts.
* Embedded Angular app in `webapp` folder for convenience. 
  You can still use `npm` commands as per [package.json](webapp/package.json) while the backend is running
  behind the [proxy](webapp/proxy.conf.json).
* Main Angular concepts ( routing, modules, components) all properly setup as per Angular guide.
* `Dockerfile` to help run the application as a container.
* Database migrations using [flyway](https://flywaydb.org/) with multi database support.
* Persistence layer powered by [Ktorm](https://www.ktorm.org/) and inspired by
  [Repository Pattern](https://www.cosmicpython.com/book/chapter_02_repository.html)
* JWT Token for authentication.
## Future Improvements

* Enable Angular `Lazy` modules.
* Add Kubernetes deployment definitions for cluster deployment.


## Technology Reference
### UI Technologies 
* Angular 12
* TypeScript 4.3+
* ng-bootstrap - CSS System
* PrimeNG- Rich UI Components 

### Server Technology
* Kotlin 1.5+
* Spring Boot 2.5+
* Gradle 7
* Ktorm
* Flyway
* H2 Embedded Database


