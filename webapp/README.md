# Vivid Angular Frontend application

This project is the frontend for the Vivid Web App.


```shell
npm run backend
npm start
```

For QA ( Running cypress e2e tests interactively )

```shell
npm run backend
npm run e2e
```

The above commands will compile and start node.js server.
Notice the (proxy.conf.json) file setting up the backend proxy
for the rest service powering the project.

## Update Angular with latest

```shell
npm outdated

ng update @angular/core@15 @angular/cli@15 \
@angular-eslint/schematics@15 @angular/cdk@15

ng update primeng@15

npm outdated
ncu --upgrade
```
