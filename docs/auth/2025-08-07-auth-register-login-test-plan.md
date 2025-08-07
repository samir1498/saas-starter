# 🧪 Register & Login Test Plan

## ✅ 1. User Registration

| Test Case ID | Description                        | Input                                          | Expected Output                      |
| ------------ | ---------------------------------- | ---------------------------------------------- | ------------------------------------ |
| REG-001      | Register with valid data           | `{ email, password, fullName }`                | `201 Created`, user saved in DB      |
| REG-002      | Register with existing email       | `{ email (already used), password, fullName }` | `409 Conflict`, error message        |
| REG-003      | Register with invalid email format | `{ email: "bademail", password, fullName }`    | `400 Bad Request`, validation error  |
| REG-004      | Register with weak password        | `{ email, password: "123", fullName }`         | `400 Bad Request`, password error    |
| REG-005      | Missing required fields            | `{}`                                           | `400 Bad Request`, validation errors |

---

## ✅ 2. User Login

| Test Case ID | Description                    | Input                                | Expected Output                     |
| ------------ | ------------------------------ | ------------------------------------ | ----------------------------------- |
| LOG-001      | Login with correct credentials | `{ email, password }`                | `200 OK`, JWT access token returned |
| LOG-002      | Login with wrong password      | `{ email, password: "wrongpass" }`   | `401 Unauthorized`, error message   |
| LOG-003      | Login with unregistered email  | `{ email: "no@user.com", password }` | `401 Unauthorized`, error message   |
| LOG-004      | Login with missing fields      | `{}`                                 | `400 Bad Request`, validation error |

---

## 🛑 3. Security Expectations

* Passwords are **hashed** in DB (not plain text).
* Token is **JWT** and contains user ID & email.
* Registration/login routes are **public**.
* Other routes require `Authorization: Bearer <token>`.
