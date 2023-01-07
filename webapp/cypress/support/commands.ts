// ***********************************************
// This example namespace declaration will help
// with Intellisense and code completion in your
// IDE or Text Editor.
// ***********************************************
 declare namespace Cypress {
   interface Chainable<Subject = any> {
    login(username: string, password: string): typeof login;
   }
 }

 function login(username: string, password: string): void {
    cy.session(
        username,
        () => {
            cy.visit('/')
            cy.get('#username').type(username)
            cy.get('#password').type(`${password}{enter}`, { log: false })
            cy.url().should('include', '/')
            cy.get('#estimatorCard > .p-card > .p-card-body > .p-card-title')
                                        .should('contain', 'Estimator')
        }
      )
 }
//
// NOTE: You can use it like so:
// Cypress.Commands.add('customCommand', customCommand);
//
// ***********************************************
// This example commands.js shows you how to
// create various custom commands and overwrite
// existing commands.
//
// For more comprehensive examples of custom
// commands please read more here:
// https://on.cypress.io/custom-commands
// ***********************************************
//

Cypress.Commands.add("login",(username:string,password:string) => login(username,password))

//
//
// -- This is a dual command --
// Cypress.Commands.add("dismiss", { prevSubject: 'optional'}, (subject, options) => { ... })
//
//
// -- This will overwrite an existing command --
// Cypress.Commands.overwrite("visit", (originalFn, url, options) => { ... })
