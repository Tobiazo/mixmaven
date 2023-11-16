# Release 3

## Table of Contents

- [Introduction to Release 3](#introduction-to-release-3)
- [Userstories](#userstories)
- [Implementation of Web Application](#implementation-of-web-application)
- [Implementation of API and Server](#implementation-of-api-and-server)
- [Updating and refactoring UI, Json and Core modules](#updating-and-refactoring-ui,-Json-and-core-modules)
- [Tests](#tests)
- [Diagrams](#diagrams)
- [Work Habits](#work-habits)
- [Checkstyle and Spotbugs](#checkstyle-and-spotbugs)

## Introduction to Release 3

Release 3 implements a new frontend using React and an API using the REST standard. The focus for this release has been development of the new React client, the springboot server and updating the JavaFX application. Along with the above Release 3 features new tests and higher test coverage along with improved Javadoc and code readability.

The decision to implement our frontend in React was a result of wanting to achive a more modern visual design and getting acquainted with new technologies commonly used in the industry. The JavaFX application remains functionally and visually similar to release 2, but the code has been updated to work with then new API. More about this [here](#updating-and-refactoring-ui,-Json-and-core-modules).

To implement our API we used a springboot server containing all endpoints. This is located in the new springboot module. [springboot](/mixmaven/springboot/README.md)


## Userstories

The Userstories used for this release can be found [here.](/docs/release-3/userstories.md)

## Implementation of Web Application

As mentioned in the introduction we have focused on creating a web application for MixMaven. Documentation for the web application can be found
[here.](/webapp/README.md)

The decision to move our focus over to developing a web application over further developing the java application was something the group discussed for some time. We had to consider whether developing a new client was beneficial despite the resulting increased workload. In the end we concluded that a web application would allow us to get experience with new technologies and a different development framework. The group considers frameworks like React modern and industry standard, and we believe React allows us to develop a better client for the end user.

The new client has the same functionality as the JavaFX client, except an option to sort the view of drinks in the webapp. The user interface is visually changed in the web application but the layout is essentially the same. Visual changes are things like changes to colours and fonts, or small layout changes.

Images of the java application and web application can be found in the following links:

- [Java Application](/mixmaven/README.md#the-app)
- [web Application](/webapp/README.md) <!--- TODO -->

## Implementation of API and Server

Release 3 implements a new API adhering to the REST design principles. The server is implemented through Springboot and contains several endpoints which is documented [here.](/docs/release-3/API.md). The API is utilized by both the JavaFX and the React client. Instructions for running the server can be found below:

- [Java Application](/mixmaven/README.md#build-and-running-the-project)
- [Web Application](/webapp/README.md) <!--- TODO -->

## Updating and refactoring UI, Json and Core modules

Since the previous release we have made some substantial changes in the UI, Json and core modules. The goal has been to support the JavaFX working both locally and remotely in a similar manner to the webapp. Furthermore we wanted to make the code more modular such that the different layers in our application were clearly defined.

To illustrate this, release 2 worked by having each UI controller interacting directly with the DataHandler in the Json module. Release 3 solves this by having the MixMavenController interacting with a DataAccess interface implemented by DirectDataAccess and RemoteDataAccess. RemoteDataAccess interacts with the API while DirectDataAccess interacts with the DataHandler. The result is that the Json and UI module now only interact through the DataAccess interface where previously each controller were dependent on the Json module.

In the Core module we added a MixMavenModel Class which contains all of our application data and the necessary methods to add, get, replace and remove drinks.

To improve maintainability, readability and improve code quality, a lot of code has been overhauled in the three modules. Long methods have been shortened by using helper methods in order to follow the single responsibility principle and make the code more readable. For example the previously long initialize method in EditDrinkController is now merely 5 lines of method calls.


## Tests

### Why do we have tests

As the project has grown in complexity and scope, unit tests has been crucial in development. The tests have been instrumental while [updating and refactoring](#updating-and-refactoring-ui,-Json-and-core-modules) to ascertain that everything is still working as intended. To maintain high code quality in our codebase we have set high standards for testing all modules.

### Test coverage

Since release 2 test coverage has been substantially improved, as testing has been a priority. With the help of **JaCOCO** we have been able to analyze our test coverage and we now have an average test coverage of about 80% in all modules. We consider this satisfactory as the remaining code would be trivial to tests (methods like "setters" or "getters") or consist of edge cases like code resulting from an IOException. 

#### Core
In the core module jacoco reports a test coverage of 84 %.

The core module is generally very simple in nature and is therefore very straightforward to test. We are very happy with the coverage in the core module. The largest part of the missing 16 % is from a constructor that exists to streamline a test.

#### Json
In the json module jacoco reports a test coverage of 78 % or 65 % depending on whether the user.home directory has been initialized or not.

The json module is somewhat difficult in nature to increase the test coverage on since a big part of the module is error handling with errors we are not able to reproduce in the tests.

#### Springboot

In the core module jacoco reports a test coverage of 81 %.

The springboot module is almost entirely tested with the biggest exception being the testing of the **replace drink** endpoint, which we did not have the time to test thoroughly. This is a point where we as a group have to take critic.

### Ui

Testing of *ui* is done in with the help of TestFX where we have written parameterized tests where a the test manually clicks around and interacts with the app, filling fields and imitating an user.

Jacoco reports a test coverage of 84 %

The ui module is a module we are very happy with the coverage off. We believe all necessary code has been thoroughly tested. The remaining 16 % mainly consists of edge cases that appear in both editDrinkController.java  and addDrinkController.java and is tested in one of these.

We do recognise that we could have achieved higher test coverage. But we are certain that our current code coverage extends over most, if not all, user cases. It is naturally possible to improve upon the test coverage in the future.

## Diagrams

<!--- TODO -->

## Work Habits

See [Work Habits Release 3](/docs/release-3/WorkHabitsRelease3
)

## Checkstyle and Spotbugs

See [Release 2](/docs/release-2/release-2.md#checkstyle)
