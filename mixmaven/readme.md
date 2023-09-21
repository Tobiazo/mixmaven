# MixMaven
MixMaven is a project in the subject IT1901. This is the project of group 31.  The App lets a user store their drink recipes. The app allows the user to add drinks from their collection.


You will find the documentation for each release in the [docs](/docs/) folder or in the following link(s).
- [Release 1](/docs/release-1/)

User stories is included in the releases.

 There is also documentation inside each module describing that module.
 - [UI](/mixmaven/core/readme.md)
- [Core](/mixmaven/core/readme.md)



## Build and running the project 
The project is run on **maven version 3.9.4** and **java version 17.0.8**. <br>
If you are in the root folder **GR2331** you need to move into the mixmaven folder. Do this with the following command: <br>
```
cd mixmaven
```
To build you need to run:
```
mvn clean install -DskipTests
``` 
In order to run the App, move into the **ui folder** and run <mark style="background-color: #7e7e7e; padding:3px; border-radius:2px"> mvn javafx:run </mark> . Like so:
```
cd ui
mvn javafx:run
```

## Testing
In order to run the included tests, use the following command in the mixmaven folder:

```
mvn test
```
### Test Coverage
We utilise JaCoCo to analyze our test coverage. This can be done by running

```
mvn clean test
mvn jacoco:report
```

Then you can go in the corresponding folder (core/ui) and inside target/site open the index.html file in a browser

## The App
The app contains two primary pages. One page to view the drinks you have stored and one page to add drinks.
