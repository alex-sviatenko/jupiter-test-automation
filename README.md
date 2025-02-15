# Test Automation Framework - API Automation with REST Assured, Spring Boot and JUnit 5

## Overview

This is a robust and scalable test automation framework built with modern **Java** technologies
**for testing RESTful APIs**. The framework leverages a variety of open-source libraries and tools to create
an efficient, reliable, and easily extensible solution for automated API testing.

## Key Technologies and Libraries

- [Java 21](https://openjdk.org/projects/jdk/21/): A modern version of Java that offers enhanced performance, security,
  and new features.
- [Maven 3.9.9](https://maven.apache.org/): The build automation tool to manage dependencies and build processes.
- [Spring Boot](https://spring.io/projects/spring-boot/): For dependency injection (DI) and easy management of
  configuration properties.
- [REST Assured](https://rest-assured.io/): A powerful REST API client library used for sending HTTP requests and
  validating responses.
- [JUnit 5](https://junit.org/junit5/): The testing framework for writing and running tests. It supports both
  traditional and parameterized tests.
- [Project Lombok](https://projectlombok.org/): To reduce boilerplate code, such as getters, setters, and constructors.
- [AssertJ](https://assertj.github.io/doc/): A fluent assertion library used for clearer and more readable assertions in
  tests.
- [Jackson](https://github.com/FasterXML/jackson): For JSON serialization and deserialization, making it easier to
  handle JSON data.
- [Log4j2](https://logging.apache.org/log4j/): A powerful and flexible logging framework used to log detailed
  information during test execution, enabling efficient troubleshooting and analysis.
- [Allure Report](https://docs.qameta.io/allure/): A framework to generate interactive and visual test reports.

## Testing Services

The framework is designed to test REST APIs from different services:

- [Reqres API](https://reqres.in/): A mock API for testing user registration and login.
- [Travelpayouts API](https://travelpayouts.github.io/slate/): An API for accessing data related to travel.

## Key Features

- **API and Data-Driven Testing:** The framework includes both traditional API tests and parameterized tests (using
  `@ParameterizedTest` in JUnit 5) to demonstrate data-driven testing approaches.
- **Parallel Test Execution:** Tests can be executed in parallel to reduce test suite runtime.
- **Logging:** Utilizes `@Log4j2` for detailed logging during test execution, with configuration managed through the
  `log4j2.xml` file.
- **Allure Reporting:** Generates detailed test execution reports to visualize results. Allure reports are available
  online at **[Allure Report](https://alex-sviatenko.github.io/rest-assured-test-automation/)**, providing
  insights into testing results and trends.

## Getting Started

### Prerequisites

- JDK 21 or higher.
- Git
- Maven for build and dependency management.
- [Allure Commandline](https://www.npmjs.com/package/allure-commandline/)

### Clone repository

1. [Fork](https://github.com/alex-sviatenko/rest-assured-test-automation/fork) the repository.
2. Clone the repository and navigate to the project

```
$ git clone https://github.com/[your_username]/rest-assured-test-automation.git
$ cd rest-assured-test-automation
```

## Running Tests

To run the tests, simply execute:

```shell
./mvnw clean test
```

For parallel execution, ensure the appropriate configurations for JUnit are set in the `junit-platform.properties` file.

## Parallel Test Execution with JUnit 5

To optimize test suite runtime, tests can be executed in parallel. This reduces the overall execution time, especially
when dealing with a large number of tests. JUnit 5 supports parallel execution of tests out of the box with simple
configuration settings.

To enable parallel test execution, you need to add the following properties to the JUnit runner configuration. These
settings control how tests are executed in parallel and can be adjusted based on your project needs.

```properties
junit.jupiter.execution.parallel.enabled=true
junit.jupiter.execution.parallel.mode.default=concurrent
junit.jupiter.execution.parallel.config.strategy=fixed
junit.jupiter.execution.parallel.config.fixed.parallelism=5
```

Let’s break down what each of these settings means:

1. `junit.jupiter.execution.parallel.enabled = true`

This setting enables the parallel execution feature of JUnit 5. By default, parallel execution is disabled, so you
need to explicitly set this property to true to activate it.

2. `junit.jupiter.execution.parallel.mode.default = concurrent`

This setting defines the default mode for test execution.

- `concurrent:` Tests will be executed concurrently (in parallel) by JUnit 5. This is the mode you'll want to use when
  aiming to reduce runtime and fully utilize parallelism.
- Other options include `same_thread` (which runs all tests in the same thread) and `dynamic` (which adapts to parallel
  execution depending on the situation).

3. `junit.jupiter.execution.parallel.config.strategy = fixed`

This setting determines how the parallel execution strategy is configured.

- `fixed:` This option allows you to specify a fixed level of parallelism, i.e., the number of test threads that should
  run concurrently. You define this value through the next setting.
- Another option, `dynamic`, adjusts the parallelism dynamically based on the system's available resources, but a fixed
  approach provides more predictable and stable execution.

4. `junit.jupiter.execution.parallel.config.fixed.parallelism = 5`

This setting specifies the fixed number of threads (parallelism) JUnit will use to run tests concurrently. In this
case, it is set to 5, meaning a maximum of `5` tests will run simultaneously. You can adjust this number based on the
available resources on your machine or CI server to find the optimal level of parallelism.

## Flaky Test Retry Mechanism

The framework includes a **Retry Flaky Tests** mechanism, enabling automatic re-runs for failing tests to ensure
stability
in case of intermittent failures. This can be configured with:

- Command-line option:

```shell
./mvnw clean test -Dsurefire.rerunFailingTestsCount=2
```

- Maven `pom.xml` configuration:

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>${maven.surefire.plugin}</version>
    <configuration>
        <rerunFailingTestsCount>2</rerunFailingTestsCount>
    </configuration>
</plugin>
```

This setup ensures that failing tests are re-run up to `2` times (or any other specified value) before they are marked
as failed. If `rerunFailingTestsCount` is set to a value smaller than or equal to 0, then it will be ignored.

## Allure Report

The reports can be generated and opened with:

- Option 1:

```shell
  allure generate ./target/allure-results/ --clean
  allure open
```

`--clean` - clean Allure report directory before generating a new one.

- Option 2:

```shell
  allure serve ./target/allure-results/
```
