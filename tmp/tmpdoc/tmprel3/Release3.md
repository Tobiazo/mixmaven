# Release 3

## Table of Contents

- [Introduction to Release 3](#introduction-to-release-3)
- [Userstories](#userstories)
- [Implementation of Web Application](#implementation-of-web-application)
- [Implementation of API and Server](#implementation-of-api-and-server)
- [Tests](#tests)
- [Diagrams](#diagrams)
- [Work Habits](#work-habits)
- [Checkstyle and Spotbugs](#checkstyle-and-spotbugs)

## Introduction to Release 3

Release 3 implements a new frontend using React and an API using the REST standard. The focus for this release has been:
-Development of the React webapp
-Refactoring the ui module to work with the api remotely 
-Developing the Springboot server
-Improving test coverage
-Work habits and code review

Our decision to implement our frontend in React was in order to achieve a more modern look and get acquainted with new technologies. No new functionality has been added to the JavaFX application but it has been updated to work both locally and remotely through the api. 

We have created a new module springboot for the server/API [springboot](/mixmaven/springboot/README.md)

## Userstories

The Userstories used for this release can be found [here.](/docs/release-3/userstories.md)

## Implementation of Web Application

As mentioned in the introduction we have focused on creating a web application for MixMaven. Documentation for the web application can be found
[here.](/webapp/README.md)

The decision to move our focus over to developing a web application over further developing the java application was something the group discussed for some time. We came to the conclusion that a web application would allow us to get experience with working with a different development environment. simultaneously the group agreed that environments like React are very modern and largely industry standard, whereas javafx is far less utilised. We also believe that React will give us the tools to development a better client for the end user.

The web application has the excact same functionality as the java application. The only small difference is the option to sort the view of all drinks.

The user interface is visually changed in the web application but the layout is essentially the same. Visual changes are things like changes to colours and fonts, or small layout changes.

Images of the java application and web application can be found in the follwing links:

- [Java Application](/mixmaven/README.md#the-app)
- [web Application](/webapp/README.md) <!--- TODO -->

## Implementation of API and Server

In the release we have also created a API and a server. Utilising REST. Both the java application and the web application utilises these. The java app is able to run without locally whereas the web application requires teh server to run. Documentation for the API can be found [here.](/docs/release-3/API.md) and information for running the applications can be found in the following links

- [Java Application](/mixmaven/README.md#build-and-running-the-project)
- [web Application](/webapp/README.md) <!--- TODO -->

## Tests

### Why do we have tests

In order to verify the quality of our codebase we have wtritten substantial tests for all of the modules. The tests are there to make sure bugs and errors do not find their way into the codebase. Our general procedure regarding testing has been to continuously write tests when the app was expanded with further functionality.

### Test coverage

We have improved upon the testing from release 2

We have strived to maintain as great as possible test coverage, utilising the dependecy **JaCoCo** in order to assist us in analyzing our test coverage. This does not mean we have strived to have 100% test coverage. This is for the simple reason that we do not believe it is necessary to test every method. Basic methods like setters and getters are generally of such a simple nature that it would be unecessary to do comprehensive testing of them.

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
