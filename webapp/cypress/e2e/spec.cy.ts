describe('Estimator App', () => {

  it('Visits Estimator List Search',()=>{
    cy.login('first_user','secret')
    cy.visit('/')
    cy.get('#estimatorLink').click()
    cy.url().should('contain','estimation/search')
    cy.get('.p-datatable-tbody > .p-element > :nth-child(1)').should('exist')
  })

  it('Searches for 1030 5th Avenue Estimator Project',()=>{
    cy.login('first_user','secret')
    cy.visit('/')
    cy.get('#estimatorLink').click()
    cy.url().should('contain','estimation/search')
    cy.get('#inputProject').type(`1030 5th Avenue {enter}`, { log: false })
    cy.get('.p-datatable-tbody > .p-element > :nth-child(1)').should('exist').click()
    cy.get('.p-selectable-row > :nth-child(1) > .p-element').contains('Roof Project')
  })

})
