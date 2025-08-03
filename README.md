
# SaaS Boilerplate (Spring Boot + Angular)

A modern, full-stack SaaS boilerplate built with Spring Boot (backend) and Angular (frontend).  
This monorepo is structured for scalability, simplicity, and developer productivity.

---

## 🛠️ Development Environment Setup

This project uses Docker Compose to manage local PostgreSQL and Redis services.

### 1. Create your `.env` file

```bash
cp .env.example .env
```

### 2. Start the dev containers

```bash
docker compose -f docker-compose.dev.yml up -d
```

### 3. Stop the dev containers

```bash
docker compose -f docker-compose.dev.yml down
```

---

## 📦 Tech Stack

* **Backend**: Java 21, Spring Boot
* **Frontend**: Angular (coming soon)
* **Database**: PostgreSQL
* **Cache**: Redis
* **Dev Env**: Docker Compose

---
