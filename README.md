# This is my solution from the challenge from [Reign](https://www.applydigital.com/reign/) 💪

### Table of content

- [Project description](#description)

- [Features](#features)

- [Technologies & Tools](#-technologies--tools)

- [Run on local](#run-on-local)

- [Test evidences](#test-evidences)

- [Authors](#authors)

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## Description

**Context**: We would like you to build a small API to test your knowledge of Java Back End Development and related technologies.

Every hour, the server should automatically make a request to the Algolia API to retrieve the data of recently published articles about [Java on Hacker News](https://hn.algolia.com/api/v1/search_by_date?query=java). It should insert the data from this API into a database and also define a REST API that the client (e.g. Postman) will use to retrieve the data.

The service should return paginated results with a maximum of 5 items, be filterable by author, _tags, title, and also be searchable by month word (e.g. september) using the "created_at" field. It should also allow the user to remove items and not have those
should not reappear when the application is restarted.

An authorization parameter with a JWT must be sent in the headers to access the endpoints.

****

![Component Diagram where is displayed the components created for the Challenge and the external API used](readme-files/components.png)

## Features

✅ `Feature 1:` Dummy Login (to generate JWT).

✅ `Feature 2:` Integration API (to populate the database)

✅ `Feature 3:` Find articles by author, tag, title, month paginated

✅ `Feature 4:` Delete an article by objectId

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## 🛠 Technologies & Tools

- **Language:** Java 21 GraalVM
- **Framework :** Spring Boot 3
- **Architecture :** Layered Architecture
- **Web framework :** Spring REST
- **Data framework :** Spring Data Mongo
- **Database :** Mongo
- **Api Docs :** Spring Doc
- **Container :** Docker and Docker compose

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Git](https://img.shields.io/badge/-Git-F05032?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/-GitHub-181717?style=for-the-badge&logo=github)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## Run on local

### Requirements
- Docker
- Postman

### steps
1. Clone the repo
2. Install java 17 (SDK Man or from another install manager)
3. inside the repo on the console run the ./mvnw test for run the unit test of the project (macOS / linux) / mvnw.cmd
   test (windows)
4. Inside the repo on the console run ./mvnw spring-boot:run to start the app / mvnw.cmd spring-boot:run (windows). The
   project runs on the default port 8080.
5. Use the postman collection to test [Postman collection](readme-files/reign.postman_collection.json) inside this collection there are the 3 generated endpoints one with the localhost URL and another with a cloud vendor url.
6. To test first you must create the Account with the account endpoint (there is examples in postman) and after that use the witdraw endpoint to generate the operations (there are examples in postman). Additionallyly there is a list endpoint to review the saved data by user.
7. Also, can enter to the swagger-ui [Swagger UI](http://localhost:8080/swagger-doc/webjars/swagger-ui/index.html)

## Test evidences

![Swagger UI test](readme-files/swagger-test.png)
![Postman test](readme-files/postman-test.png)
![Jacoco report](readme-files/jacoco-report.png)

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## Authors

| [<img src="https://avatars.githubusercontent.com/u/6700707?v=4" width=115><br><sub>Samuel Gonzales</sub>](https://github.com/samusfree) |  
|:---------------------------------------------------------------------------------------------------------------------------------------:|
