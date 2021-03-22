#Vivid Web Application
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
This project utilizes `Angular/TypeScript` and the 
powerful JVM based `Kotlin` language, matched with enterprise ready `Spring Boot` MVC Framework to 
power the backend services.  There are many frameworks/languages out there to choose from, and all of them
have something unique and wonderful about them.  This project is meant to showcase the evolution, challenges
and magic of Angular, TypeScript, Kotlin, and MVC Spring Framework when operating together.

Use the [HELP.md](HELP.md) to help you set up your environment and get started.

## Major Features

* `MultiDataSource`- Multiple [H2 Databases](src/main/kotlin/com/vivid/graff/config/H2DatabaseConfiguration.kt) created to show how to direct user requests based on tenant subdomain and its unique credentials. 
* Security - User login, unique to tenant Database. `VividAuthenticationProvider` shows how you can fulfill unique requirements. 
* Using [Entities.kt](src/main/kotlin/com/vivid/graff/Entities.kt) to define business data models. Estimates with projects and proposals.
* Using Gradle with 'node.js' plugin to manage both Angular and Spring/kotlin artifacts.
* Embedded Angular app in `webapp` folder for convenience. 
  You can still use `npm` like always. It's all setup as per [package.json](webapp/package.json) Just don't forget to start
  the backend for the [proxy](webapp/proxy.conf.json) to work.
* Main Angular concepts ( routing, modules, components) all properly setup as per Angular guide.
* `Dockerfile` to help run the application as a container.

## Future Improvements
* Migration Technology such as or similar to [flyway](https://flywaydb.org/)
* Explore better ORM solutions developed in `Kotlin`. Currently, using raw database operations using `JDBCTemplate` 
for speed and direct access to database. Something along the lines of  [Repository Pattern](https://www.cosmicpython.com/book/chapter_02_repository.html) ???
* Enable Angular `Lazy` modules.
* Switch form Session cookies to local storage JWT Token.
* Add Kubernetes deployment definitions for cluster deployment.


## Technology Reference
### UI Technologies 
* Angular 11
* TypeScript 4.1
* ng-bootstrap - CSS System
* PrimeNG- Rich UI Components 

### Server Technology
* Spring Boot 2.4 + Kotlin
* Gradle 6 + Kotlin
* Kotlin using JVM15 bytecode
* H2 Embedded Database


