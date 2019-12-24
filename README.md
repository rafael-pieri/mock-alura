[![Build Status](https://travis-ci.com/rafael-pieri/mock-alura.svg?branch=master)](https://travis-ci.com/rafael-pieri/mock-alura)

## Mock: Automatic Test in Java

### Overview
In object-oriented programming, mock objects are simulated objects that mimic the behavior of real objects in controlled ways. 
A programmer typically creates a mock object to test the behavior of some other object.

This repository contains some examples of how to use mocks in automatic testing. Below are listed the topics covered:

#### Simulating Behaviors with Mock Objects
* The problem of depending on other systems
* Mockito framework
* Simulating invocation of a method
* when() and thenReturn() methods

#### Ensuring that methods were invoked
* Simulating methods that return nothing
* Ensuring that a method was invoked
* verify() method

#### Mocks throwing exceptions
* Simulating throw exceptions
* doThrow() method

#### Capturing arguments received by Mock
* Testing content of parameters that are passed to methods
* ArgumentCaptor class

#### Isolating to test
* Difficulty testing code that does a lot
* Good Code Practice
* Separating Responsibilities to Facilitate Testing

### How to run all tests successfully
Execute the following command to up a MYSQL instance:

```docker run --name mysql-instance -e MYSQL_ROOT_PASSWORD=root -d mysql:8.0.11```

Once the database is up and running, execute the following command to run the automatic tests:

```mvn test```
