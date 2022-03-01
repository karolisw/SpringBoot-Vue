This is a REST API (backend) for use alongside Vue Calculator (frontend)

resources/application.properties:
- Contains the Logger configuration specifically per package where/when needed.
- Does not contain H2 (database dependency in pom.xml) configurations (username/password), because Spring Boot automatically does it.

Package descriptions within 'karolisw.rest.oving.springbootrestcalculator' package:

controller:
- RestController.java handles web communication, meaning that it is the top layer of this backend. 
- The rest of the layers will be abstracted away (not visible to the frontend).

model:
- Expression.java contains the model of the mathExpression object.
- Expression.java is the entity used for the database.

repo:
- Contains the repository interface ExpressionRepository, meaning that it extends JpaRepository.
- This means that the interface provides the backend with functionality for db communication such as deleting, saving, updating, storing etc. *without* writing any code (as it extends Jpa)

service:
- Contains the backend functionality "seen" by the frontend, meaning that it does the actual calculation of the expressions to be evaluated.
