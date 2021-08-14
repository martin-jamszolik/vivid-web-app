# Getting Started

### Tools
For best setup experience, recommend the following.

* Install [SDKMAN](https://sdkman.io/) so we can manage gradle, and it's dependencies.

```shell
sdk list java
sdk install java 15.0.2.hs-adpt 
sdk use java 15.0.2.hs-adpt
sdk install gradle
```


### Build and Run
This project helps you manage both frameworks (Angular, Spring Boot) with just Gradle:

* Execute the following for a quick start

```shell
gradle bootRun 
```
The above commands will build `webapp` angular and sprint boot together.
See [build.gradle.kts](build.gradle.kts) node section for details. 

### Trying the Application
You want to see the multi-tenant aspect in action. Update your host file so that we can try a subdomain access.

```shell
sudo nano /etc/hosts
#Add the following to the content of the file
127.0.0.1   first.local.me second.local.me
#Save
```
The above will let you issue the following URLs:
* http://first.local.me:8080
* http://second.local.me:8080

At this point, the application will switch between two different data sources ( tenants ), serving 
two different customers with the same runtime. 

Checkout the H2 Database setup for login details. See [seed-hd2-1.sql](src/main/resources/templates/seed-h2-1.sql)
, you will find a `first_user` with password `secret` created. Use that to login.

### Docker Tips
To try the application with docker, do the following
```shell
gradle bootJar
docker build -t vivid .
docker run --rm -p 8080:8080 vivid
```

