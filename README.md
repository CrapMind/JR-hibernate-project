# 🗃️ RPG Hibernate Project
This is a project of the **JavaRush** University.

This is a sample web application for managing RPG player accounts. It is built with **Spring MVC**, **Hibernate** and **PostgreSQL**.

The project exposes REST endpoints under `/rest/players` and includes a small JavaScript UI (`src/main/webapp/html/my.html`) for interacting with the API.

## ✨ Features

- CRUD operations on `Player` entities
- Hibernate database access (`PlayerRepositoryDB`) with optional in-memory repository (`PlayerRepositoryMemory`)
- Simple web interface using jQuery and Thymeleaf
- Sample SQL data (`src/main/resources/init.sql`)

## 🏗️ Building

This project uses Maven. To build the WAR package run:

```bash
mvn clean package
```

The application expects a running PostgreSQL instance. Database credentials are passed via system properties `USER_NAME` and `USER_PASS` when the application starts.

## 🚀 Running

Deploy the generated `rpg-hibernate.war` to a servlet container such as Tomcat. For example:

```bash
CATALINA_OPTS="-DUSER_NAME=postgres -DUSER_PASS=secret" catalina.sh run
```

Then open `http://localhost:8080/` to access the web UI.

## 📂 Repository Structure

- `src/main/java/com/game` – Spring configuration, controllers, entities, repositories and services
- `src/main/webapp` – HTML, CSS and images used by the UI
- `src/main/resources` – Hibernate configuration and SQL scripts

## 📄 License

This project is provided for educational purposes and has no specific license.
