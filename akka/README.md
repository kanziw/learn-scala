# Akka Study

## Bootstrap
```bash
g8 https://github.com/akka/akka-http-scala-seed.g8 --name=akka
```



### Scripts

### Run
```bash
sbt run
```

### Test
```bash
sbt test
```



## DB

### run

```bash
docker-compose up
```

### Initialization

```sql
CREATE DATABASE test;
CREATE TABLE `user` (
  `name` varchar(16) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(32) NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

```



## API

### v1

* GET /
  * "ROUTE"
* GET /users
  * { users: [USER] }
* POST /users
  * body : `{ name: String, age: Int, countryOfResidence: String } `
  * { description: "User {NAME} created." }
  * curl example: `curl -H "Content-Type: application/json" -X POST -d '{"name":"kanziw","age":31,"countryOfResidence":"JY"}' http://127.0.0.1:8080/users`
* GET /users/:NAME
  * { name: String, age: Int, countryOfResidence: String } => USER
* DELETE /users/:NAME
  * { description: "User {NAME} deleted." }
  * curl example: `curl -X DELETE http://127.0.0.1:8080/users/kanziw`

### v2

> API version 을 올리면서 v2 로 라우팅 하는 시나리오

* GET /v2/users : v1 과 같음
* POST /v2/users : v1 과 같음
* GET /v2/users/:NAME : v1 과 같음
* DELETE /v2/users/:NAME : v1 과 같음

