# Banker API

We participate in thousands of auctions every second. In order to achieve this, multiple
instances of services act simultaneously.
Each of these instances need to know if the campaign they are about to bid for has money.
The Banker’s job is to handle the money.

## Getting Started

Clone or download the project 

Type the command below on terminal 
```
mvn instal
```
Make Sure redis server is running on the default port(6379)

Then run the command below 
```
java -jar target\BankerApi-1.0-SNAPSHOT.jar
```

## Running the tests
For running tests run the command below on terminal 
```
mvn test
```

## Built With

* [spring-boot](https://spring.io/projects/spring-boot) - The framework used
* [Maven](https://maven.apache.org/) - Dependency Management
