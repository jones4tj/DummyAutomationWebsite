Feature: Title of your feature
  tests scenarios for end-to-end testing

  Background: Navigate to login page
    When I navigate to the login page

  Scenario Outline: Test required login fields
    When I attempt to login
    And I enter my username <username>
    And I attempt to login
    And I enter my password <password>
    And I attempt to login
    Then I logout

    Examples: 
      | username          | password     |
      | "standard_user"   | "secret_sauce" |
      | "locked_out_user" | "secret_sauce" |

  Scenario Outline: Add product to cart
  	When I enter my username <username> and password <password>
  	And I attempt to login
  	And I add product <product> to cart
  	Then I logout
  	
  	Examples:
  	| username        | password       | product               |
  	| "standard_user" | "secret_sauce" | "Sauce Labs Backpack" |
  	
  Scenario Outline: Sort products
 	  When I enter my username <username> and password <password>
 	  And I attempt to login
 	  And I sort products <sortOption>
 	  Then I logout
 	
 	  Examples:
 	  | username        | password       | sortOption  |
 	  | "standard_user" | "secret_sauce" | "AtoZ"      |
 	  | "standard_user" | "secret_sauce" | "ZtoA"      |
 	  | "standard_user" | "secret_sauce" | "LowtoHigh" |
 	  | "standard_user" | "secret_sauce" | "HightoLow" |
 	  
 	Scenario Outline: Remove product from cart
 	  When I enter my username <username> and password <password>
 	  And I attempt to login
 	  And I add product <product> to cart
 	  Then I navigate to cart page
 	  And I remove product <product> from cart
 	  And I logout
 	
 	  Examples:
 	  | username        | password       | product               |
  	| "standard_user" | "secret_sauce" | "Sauce Labs Backpack" |
  	
  Scenario Outline: Continue shopping from cart page
    When I enter my username <username> and password <password>
    And I attempt to login
    Then I navigate to cart page
    And I continue shopping
    And I logout
    
    Examples:
    | username        | password       |
    | "standard_user" | "secret_sauce" |
    
  Scenario Outline: Checkout
    When I enter my username <username> and password <password>
    And I attempt to login
    Then I navigate to cart page
    And I checkout
    And I logout
    
    Examples:
    | username        | password       |
    | "standard_user" | "secret_sauce" |
    
  Scenario Outline: Test required fields for checkout info
    When I enter my username <username> and password <password>
    And I attempt to login
    And I navigate to cart page
    And I checkout
    Then I test required checkout info fields <firstName> <lastName> <postalCode>
    And I logout
    
    Examples:
    | username        | password       | firstName | lastName | postalCode   |
    | "standard_user" | "secret_sauce" | "John"    | "Test"   | "postalCode" |
    
  Scenario Outline: Cancel Checkout from Checkout Info Page
  	When I enter my username <username> and password <password>
  	And I attempt to login
  	And I navigate to cart page
  	And I checkout
  	Then I cancel checkout
  	And I logout
  	
  	Examples:
    | username        | password       |
    | "standard_user" | "secret_sauce" |
    
  Scenario Outline: Continue Checkout from Checkout Info Page
  	When I enter my username <username> and password <password>
  	And I attempt to login
  	And I navigate to cart page
  	And I checkout
  	Then I continue checkout <firstName> <lastName> <postalCode>
  	And I logout
  	
  	Examples:
    | username        | password       | firstName | lastName | postalCode   |
    | "standard_user" | "secret_sauce" | "John"    | "Test"   | "postalCode" |
    
  Scenario Outline: Verify total in checkout overview page
  	When I enter my username <username> and password <password>
  	And I attempt to login
  	And I add product <product> to cart
  	And I navigate to cart page
  	And I checkout
  	Then I continue checkout <firstName> <lastName> <postalCode>
  	And I verify total in checkout overview page
  	And I logout
  	
  	Examples:
    | username        | password       | product               |firstName | lastName | postalCode   |
    | "standard_user" | "secret_sauce" | "Sauce Labs Backpack" |"John"    | "Test"   | "postalCode" |
    
  Scenario Outline: Verify header and back home button in checkout complete page
  	When I enter my username <username> and password <password>
  	And I attempt to login
  	And I add product <product> to cart
  	And I navigate to cart page
  	And I checkout
  	Then I continue checkout <firstName> <lastName> <postalCode>
  	And I finish checkout
  	And I verify header <header>
  	And I click back home button
  	And I logout
  	
  	Examples:
    | username        | password       | product               |firstName | lastName | postalCode   | header                      |
    | "standard_user" | "secret_sauce" | "Sauce Labs Backpack" |"John"    | "Test"   | "postalCode" | "Thank you for your order!" |
