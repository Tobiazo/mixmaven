/// <reference types="cypress" />

describe('home page', () => {
  beforeEach(() => {
    cy.intercept('GET', '**/drinks', { fixture: 'drinks.json' }).as('getDrinks')
    // cy.intercept('DELETE', '**/drinks/**', { fixture: 'drinks.json' }).as('deleteDrink')
    cy.visit('http://localhost:3000')
  })

  it('Checks that elments are on the page', () => {
    cy.get('h1').should('have.text', 'MiXMaven')
    cy.get('nav li')
      .should('have.length', 2)
      .should('contain', 'Home')
      .should('contain', 'New drink')
    cy.get('.layout-footer').should('exist')
  })

  it('should navigate correctly', () => {
    cy.get('nav li').last().click()
    cy.url().should('include', '/new')
    cy.get('nav li').first().click()
    cy.url().should('include', '/')
  })

  it('should fetch drinks and display them correctly', () => {
    cy.get('.drink-box .drink-card').should('have.length', 2)
    cy.get('.drink-box .drink-card').first().contains('Vodka shot')
    cy.get('.drink-box .drink-card').last().contains('GT')

    cy.get('.drink-box .drink-card')
      .last()
      .within(() => {
        cy.get('.card-content').should('not.exist')
      })

    cy.get('.drink-box .drink-card').last().click()

    cy.get('.drink-box .drink-card')
      .last()
      .within(() => {
        cy.get('.card-content').should('exist')
      })
  })

  it('should be possible to delete a drink', () => {
    // need to intercept DELETE request or something
    // cy.get(".drink-card").should("have.length", 2)
    // cy.get(".drink-card").last().click()
    // cy.get(".drink-card").last().within(() => {
    //     cy.get(".icon-delete").click()
    // })
    // cy.get(".drink-card").should("have.length", 1)
  })
})
