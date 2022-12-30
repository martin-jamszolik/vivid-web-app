describe('Login Page', () => {
  it('Visits the initial project page', () => {
    cy.visit('/')
    cy.get('.text-600').contains('Welcome to Vivid Application')
  })
})
