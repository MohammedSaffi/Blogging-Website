# Blog Portal

## Stack

- Backend: Spring Boot 4.1.1, Java 21, Spring Data JPA, Hibernate
- Database: PostgreSQL
- Frontend: React.js

## PostgreSQL setup

The backend uses the `blog_portal` database. The local Docker Compose file creates it:

```powershell
cd "Blog-Portal-Backend\src\main\resources"
docker compose up -d
```

The default local Compose credentials are `postgres` / `postgres`. Configure another database with environment variables before starting the backend:

- `DB_URL`, default `jdbc:postgresql://localhost:5432/blog_portal`
- `DB_USERNAME`, default `postgres`
- `DB_PASSWORD`, default `postgres` for the local Compose database
- `JPA_DDL_AUTO`, default `update`
- `ADMIN_EMAIL`, `ADMIN_PASSWORD`, `ADMIN_FIRST_NAME`, `ADMIN_LAST_NAME`

Passwords are not stored in source configuration. For shared or production environments, set `DB_PASSWORD` and use a migration-managed schema rather than `JPA_DDL_AUTO=update`.

Java 21 is required. Spring Boot 4.1.1 uses the Jakarta namespace for persistence and validation APIs.

## Run the applications

Build and start the backend from its module directory:

```powershell
cd Blog-Portal-Backend
& "C:\Program Files\apache-maven-3.9.16\bin\mvn.cmd" clean package
& "C:\Program Files\apache-maven-3.9.16\bin\mvn.cmd" spring-boot:run
```

Start the React frontend in a second terminal:

```powershell
cd Blog-Portal-Frontend
npm install
npm start
```

The REST endpoint paths, HTTP methods, DTOs, authentication behavior, and response contracts remain unchanged. No frontend changes were required for this Java/Spring upgrade.

## Relational schema

Hibernate creates these tables when `JPA_DDL_AUTO=update`:

- `users`: user profile, role, encrypted password, and unique email
- `posts`: post content and status with a foreign key to `users`
- `comments`: message with foreign keys to `posts` and `users`
- `reactions`: boolean reaction with foreign keys to `posts` and `users`; one row per user/post
- `reports`: report relationship with foreign keys to `posts` and `users`; one row per user/post

All identifiers remain UUID strings so existing request and response payloads are compatible. Relationships are lazy unidirectional `ManyToOne` mappings, so entities do not serialize circular child collections.

For a new local installation, the `AdminUserInitializer` creates the configured admin account on startup. The current application uses PostgreSQL only; MongoDB is not a runtime dependency.
