# 📝 Project Plan – SaaS Starter (Backend + Frontend)

Goal: Build a clean multi-tenant SaaS starter with Spring Boot (backend) and Angular (frontend).

---

## ✅ Step 1: Dev Environment (Done)

* Docker Compose: Postgres + Redis
* `.env.example` created
* Initial README with setup steps
* Pushed to `chore/dev-env-setup` branch

---

## 🔨 Step 2: Backend – API Setup

* [ ] Create Spring Boot app (`saas-backend`)
* [ ] Connect to Postgres using `.env`
* [ ] `/health` endpoint
* [ ] Confirm DB connection

---

## 🔐 Step 3: Backend – Auth (JWT)

* [ ] `User` entity (id, email, password, etc.)
* [ ] Register + Login endpoints
* [ ] Hash password (BCrypt)
* [ ] Issue JWT on login
* [ ] Auth middleware

---

## 🏢 Step 4: Backend – Multi-Tenancy

* [ ] `Organization` entity
* [ ] Link users to organizations
* [ ] Require org context (e.g. header)
* [ ] Protect resources per-org

---

## 🧪 Step 5: Backend – Seed Data + Local Testing

* [ ] Seed default org + admin user
* [ ] Manual testing with Postman/cURL

---

## 🌐 Step 6: Frontend – Angular Setup

* [ ] Generate Angular workspace (`saas-frontend`)
* [ ] Setup environments (`dev`, `prod`)
* [ ] Create auth module: login + register forms
* [ ] Connect to backend via `HttpClient`
* [ ] Store JWT in localStorage or cookie
* [ ] Setup route guards

---

## 🏠 Step 7: Frontend – Basic Pages

* [ ] Dashboard (dummy content)
* [ ] Organization switcher (placeholder)
* [ ] Profile page (view email, logout)

---

## 💡 Optional Next Steps (Future)

* Invite users to org
* User roles + permissions
* Audit logs
* Billing (Stripe)
* UI theme system
* Add OAuth/SSO (not hand-rolled)
