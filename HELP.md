# Getting Started

### Tools
For best setup experience, recommend the following.

* Install [SDKMAN](https://sdkman.io/), so we can manage gradle, and it's dependencies.

```shell
sdk list java
sdk install java 17.0.1-tem
sdk use java 17.0.1-tem
sdk install gradle
```


### Build and Run
This project helps you manage both frameworks (Angular, Spring Boot) with just Gradle:

* Execute the following for a quick start

```shell
gradle bootRun 
```
The above commands will the parent backend application as 
well as the frontend in `webapp` folder.
See [build.gradle.kts](build.gradle.kts) node section for details. 

### Trying the Application
You want to see the multi-tenant aspect in action. Update your host file so 
that we can try access by subdomain.

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
two different customers within the same runtime. But remember, you don't
have to use the subdomain approach if you don't want to. All that it matters is, 
you application needs to create a valid authenticated user. Your frontend just needs to
pass the default `customer` context, you can just set a default one. That's how
cypress e2e works.

Checkout the H2 Database setup for login details. See [seed-hd2-1.sql](src/main/resources/templates/seed-h2-1.sql)
, you will find a `first_user` with password `secret` created. Use that to login.

### Running frontend using Node.js directly
You can run the Angular frontend as per standard documentation as well instead of Gradle.
```shell
cd webapp
npm install
npm start

#In another terminal session run the backend
npm run backend
# Check package.json for all the available commands
```

### E2E Tips (Cypress)
You can also experience the application using cypress tooling.
```shell
cd webapp
npm ci
npm run backend
# Using the interactive UI
npm run e2e
# Or if you just want to run headless
npm run e2e:ci
```

### Docker Tips
To try the application with docker, do the following
```shell
gradle bootJar
docker build -t vivid .
docker run --rm -p 8080:8080 vivid
```

