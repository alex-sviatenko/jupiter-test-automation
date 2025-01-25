# Test Automation Project

This is a test automation project for JUPITER platform (demo version).

## Project components

Given test automation project is built with next key frameworks and technologies:

- [Java 21](https://openjdk.org/projects/jdk/21/);
- [Maven 3.9.9](https://maven.apache.org/);
- [Spring](https://spring.io/) for bean lifecycle management, dependency injection and configuration properties;
- [REST Assured](https://rest-assured.io/) REST API client library;
- [Project Lombok](https://projectlombok.org/)
- [JUnit 5](https://junit.org/junit5/) testing framework
- [AssertJ](https://assertj.github.io/doc/) for assertions;
- [Allure](https://docs.qameta.io/allure/) reporting framework
- [Jackson](https://github.com/FasterXML/jackson) for serialization/deserialization

## Installation guide

Project usage requires next to be installed:

- Java 21;
- Git;
- [Allure CLI](https://www.npmjs.com/package/allure-commandline)

## Clone the project from GitHub:

```
$ cd project_repo
$ git clone git@github.com:alex-sviatenko/jupiter-test-automation.git
```

# Running Tests

Example:

```shell
./mvnw clean test
```
## Parallel Test Execution

 To run test in parallel mode use Junit runner with following properties:
> systemProperty "junit.jupiter.execution.parallel.enabled", true   
> systemProperty "junit.jupiter.execution.parallel.mode.default", "concurrent"  
> systemProperty "junit.jupiter.execution.parallel.mode.classes.default", "concurrent"  
> systemProperty "junit.jupiter.execution.parallel.config.strategy", "fixed"  
> systemProperty "junit.jupiter.execution.parallel.config.fixed.parallelism", 3

## Retry Flaky Tests

You may re-run flaky tests. Set the **rerunFailingTestsCount** property to be a value larger than 0. Tests will be run until they pass or the number of reruns has been exhausted.
```shell
./mvnw clean test -Dsurefire.rerunFailingTestsCount=2 
```
You may add the configuration directly to your pom.xml file under the Surefire plugin configuration. This way, you won't need to pass the **-Dsurefire.rerunFailingTestsCount** property on the command line.
```
<plugin>
   <groupId>org.apache.maven.plugins</groupId>
   <artifactId>maven-surefire-plugin</artifactId>
   <version>${maven.surefire.plugin}</version>
   <configuration>
      <rerunFailingTestsCount>2</rerunFailingTestsCount>
   </configuration>
</plugin>
```
This will configure the Surefire plugin to automatically rerun failing tests 2 times without needing to pass the property via the command line.
If **rerunFailingTestsCount** is set to a value smaller than or equal to 0, then it will be ignored.


# Creating Allure report

To create allure report execute command:
1. If you need to save the report for future reference or for sharing it with colleagues. It will generate test results and saves an HTML report into the **allure-report** directory
```
$ allure generate ./target/allure-results/ --clean
$ allure open
```

2. If you need to create the same report as **allure generate**, but put it into a temporary directory and start a local web server configured to show this directory's contents. The command then automatically opens the main page of the report in a web browser.

```
$ allure serve ./target/allure-results/
```
