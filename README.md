
# Worker's Payroll microservice
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=flat&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-6DB33F?style=flat&logo=spring&logoColor=white) 
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=flat&logo=postgresql&logoColor=white)
![H2 DB](https://img.shields.io/badge/H2Database-0000BB?style=flat&logo=openjdk&logoColor=F1E712)
[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](./LICENSE)

### Challenge
The aim of this hands-on practical project is to create a microservice that calculates workers payroll.

Created during Udemy Microservices course

#### Proposal
To create a complete functional microservice for payroll calculation using Java with Spring Boot and Spring Cloud Microservices: Java 11 | Spring Boot 2.3.4.

## Installation

1. Clone the repository:
```bash
git clone https://github.com/samanthamaiaduarte/microservice-project.git
```
2. Open the project directory ``microservice-project`` in your IDE

3. Wait until all dependencies be downloaded

4. Execute the projects one-by-one, following the order:<br/>
a. hr-config-server<br/>
b. hr-eureka-server<br/>
c. hr-apigateway-zuul<br/>
d. hr-oauth<br/>
e. hr-payroll<br/>
f. hr-user<br/>
g. hr-worker

Projects will run using a ``test`` profile, connecting at a H2 database.
<br/><br/>
It's also possible to use a PostgreSQL database changing profile to "dev". If you want to try, after steps 1, 2 and 3 above, do this:

1. Create a database named ``db_hr_worker`` in your PostgreSQL Server

2. Execute the script ``create.sql`` located in ``hr-worker`` folder in the ``db_hr_worker`` database

3. Execute the script ``data.sql`` located in ``hr-worker/src/main/resources`` folder in the ``db_hr_worker`` database

4. Create a database named ``db_hr_user`` in your PostgreSQL Server

5. Execute the script ``create.sql`` located in ``hr-user`` folder in the ``db_hr_user`` database

6. Execute the script ``data.sql`` located in ``hr-user/src/main/resources`` folder in the ``db_hr_user`` database

7. Fork the configuration repository:
```
https://github.com/samanthamaiaduarte/microservice-project-config.git
```
6. In the GitHub repository you just forked, edit ``hr-user-dev.properties`` file, replacing:<br/>
a. {SERVER_NAME} for your PostgreSQL server name or ip address<br/>
b. {PORT} for your server port<br/>
c. {DATABASE_NAME} for the database name ``db_hr_user`` you just created<br/>
d. {USERNAME} for the database access username<br/>
e. {PASSWORD} for the database access password
```bash
spring.datasource.url=jdbc:postgresql://{SERVER_NAME}:{PORT}/{DATABASE_NAME}
spring.datasource.username={USERNAME}
spring.datasource.password={PASSWORD}
```
7. In the GitHub repository you just forked, edit ``hr-worker-dev.properties`` file, replacing:<br/>
a. {SERVER_NAME} for your PostgreSQL server name or ip address<br/>
b. {PORT} for your server port<br/>
c. {DATABASE_NAME} for the database name ``db_hr_worker`` you just created<br/>
d. {USERNAME} for the database access username<br/>
e. {PASSWORD} for the database access password
```bash
spring.datasource.url=jdbc:postgresql://{SERVER_NAME}:{PORT}/{DATABASE_NAME}
spring.datasource.username={USERNAME}
spring.datasource.password={PASSWORD}
```
8. In the project ``hr-config-server``, edit ``application.properties`` file (folder src/main/resources), changing the github path and branch (if it's necessary):<br/>
a. {GITHUB_REPOSITORY_URL} the configuration repository you just forked<br/>
b. {BRANCH_NAME} branch name in that repository (usually is main)
```bash
spring.cloud.config.server.git.uri={GITHUB_REPOSITORY_URL}
spring.cloud.config.server.git.default-label={BRANCH_NAME}
```
8. In the projects bellow, edit ``bootstrap.properties`` file, setting active profile ``dev``. The projects:<br/>
a. hr-apigateway-zuul<br/>
b. hr-oauth<br/>
c. hr-user<br/>
d. hr-worker
```bash
spring.profiles.active=dev
```
9. Execute the projects one-by-one, following the order:<br/>
a. hr-config-server<br/>
b. hr-eureka-server<br/>
c. hr-apigateway-zuul<br/>
d. hr-oauth<br/>
e. hr-payroll<br/>
f. hr-user<br/>
g. hr-worker

## Acess information

There are two users available in the database:<br/>
USER: ``leia@gmail.com``<br/>
PASS: ``123456``<br/>
ROLE: ``ADMIN`` and ``OPERATOR``<br/>
<br/>
USER: ``nina@gmail.com``<br/>
PASS: ``123456``<br/>
ROLE: ``OPERATOR``

As for the roles, ``OPERATOR`` only can read worker information. Other operations can only be executed by ``ADMIN``.

It's also necessary to know client-name and client-secret, to get the token:<br/>
CLIENT-NAME: ``myappname123``<br/>
CLIENT-SECRET: ``myappsecret123``

## API Reference

### Login
```http
POST http://localhost:8765/auth/oauth/token
```
Authorization: ``Basic``<br/>
Username: ``CLIENT-NAME``<br/>
Password: ``CLIENT-SECRET``<br/>

Body: ``urlencoded``<br/>
username: ``one of the users``<br/>
password: ``user password``<br/>
grant_type: ``password``<br/>

Response:
```
{
    "access_token": "GENERATED_TOKEN",
    "token_type": "bearer",
    "expires_in": 7199,
    "scope": "read write",
    "jti": "9bf8fbb5-c214-4804-b03f-c34aec964cc7"
}
```
### Find user by email
```http
GET http://localhost:8765/user/users/search?email=leia@gmail.com
```
Authorization: ``Bearer``<br/>
Token: ``GENERATED_TOKEN``<br/>

Parameters:
| Parameter | Type     | Description |
| :-------- | :------- | :---------- |
| `email`   | `string` | User email  |

Response:
```
{
    "id": 2,
    "name": "Leia Red",
    "email": "leia@gmail.com",
    "password": "$2a$10$NYFZ/8WaQ3Qb6FCs.00jce4nxX9w7AkgWVsQCG6oUwTAcZqP9Flqu",
    "roles": [
        {
            "id": 1,
            "name": "ROLE_OPERATOR"
        },
        {
            "id": 2,
            "name": "ROLE_ADMIN"
        }
    ]
}
```
| Data        | Type      | Description   |
| :---------- | :-------- | :------------ |
| `id`        | `integer` | User id       |
| `name`      | `string`  | User name     |
| `email`     | `string`  | User email    |
| `password`  | `string`  | User password |
| `role.id`   | `integer` | Role id       |
| `role.name` | `string`  | Role name     |

### Find user by id
```http
GET http://localhost:8765/user/users/{id}
```
Authorization: ``Bearer``<br/>
Token: ``GENERATED_TOKEN``<br/>

Parameters:
| Parameter | Type      | Description |
| :-------- | :-------- | :---------- |
| `id`      | `integer` | User id     |

Response:
```
{
    "id": 1,
    "name": "Nina Brown",
    "email": "nina@gmail.com",
    "password": "$2a$10$NYFZ/8WaQ3Qb6FCs.00jce4nxX9w7AkgWVsQCG6oUwTAcZqP9Flqu",
    "roles": [
        {
            "id": 1,
            "name": "ROLE_OPERATOR"
        }
    ]
}
```
| Data        | Type      | Description   |
| :---------- | :-------- | :------------ |
| `id`        | `integer` | User id       |
| `name`      | `string`  | User name     |
| `email`     | `string`  | User email    |
| `password`  | `string`  | User password |
| `role.id`   | `integer` | Role id       |
| `role.name` | `string`  | Role name     |

### Find all workers
```http
GET http://localhost:8765/worker/workers
```
Authorization: ``Bearer``<br/>
Token: ``GENERATED_TOKEN``<br/>

Response:
```
[
    {
        "id": 1,
        "name": "Bob Green",
        "dailyIncome": 200.0
    },
    {
        "id": 2,
        "name": "Maria Brown",
        "dailyIncome": 300.0
    },
    {
        "id": 3,
        "name": "Alex Grey",
        "dailyIncome": 250.0
    }
]
```
| Data          | Type      | Description         |
| :------------ | :-------- | :------------------ |
| `id`          | `integer` | Worker id           |
| `name`        | `string`  | Worker name         |
| `dailyIncome` | `double`  | Worker daily income |

### Find worker by id
```http
GET http://localhost:8765/worker/workers/{id}
```
Authorization: ``Bearer``<br/>
Token: ``GENERATED_TOKEN``<br/>

Parameters:
| Parameter | Type      | Description |
| :-------- | :-------- | :---------- |
| `id`      | `integer` | Worker id   |

Response:
```
{
    "id": 1,
    "name": "Bob Green",
    "dailyIncome": 200.0
}
```
| Data          | Type      | Description         |
| :------------ | :-------- | :------------------ |
| `id`          | `integer` | Worker id           |
| `name`        | `string`  | Worker name         |
| `dailyIncome` | `double`  | Worker daily income |

### Generates payment value
```http
GET http://localhost:8765/payroll/payments/{id}}/days/{days}
```
Authorization: ``Bearer``<br/>
Token: ``GENERATED_TOKEN``<br/>

Parameters:
| Parameter | Type      | Description                 |
| :-------- | :-------- | :-------------------------- |
| `id`      | `integer` | Worker id                   |
| `days`    | `integer` | Number of days to calculate |

Response:
```
{
    "name": "Bob Green",
    "dailyIncome": 200.0,
    "days": 20,
    "total": 4000.0
}
```
| Data          | Type      | Description           |
| :------------ | :-------- | :-------------------- |
| `name`        | `string`  | Worker name           |
| `dailyIncome` | `integer` | Worker daily income   |
| `days`        | `integer` | Number of days worked |
| `total`       | `double`  | Worker total payment  |

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request to the repository.

When contributing to this project, please follow the existing code style, [commit conventions](https://www.conventionalcommits.org/en/v1.0.0/), and submit your changes in a separate branch.
