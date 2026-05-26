# 🚀 BFHL REST API - Spring Boot

A production-ready Spring Boot REST API built for the **Bajaj Finserv Health Dev Challenge** (BFHL).

## 👤 Developer Details
*   **Full Name:** Karuna Jain
*   **Date of Birth:** `02082005` (02-08-2005)
*   **Email:** `Karunajain230183@acropolis.in`
*   **Roll Number:** `0827CS231125`
*   **User ID:** `karuna_jain_02082005`

---

## 📡 API Documentation

### 1. GET `/bfhl`
*   **Description:** Verification endpoint to check server availability and return operation code.
*   **Response (200 OK):**
    ```json
    {
      "operation_code": 1
    }
    ```

### 2. POST `/bfhl`
*   **Description:** Processes a list of input strings and classifies them into numbers (even/odd), alphabets, and special characters. Also computes the sum of numbers and a modified concatenated string of characters.
*   **Request JSON Body:**
    ```json
    {
      "data": ["a", "1", "334", "$", "y", "B"]
    }
    ```
*   **Response JSON Body (200 OK):**
    ```json
    {
      "is_success": true,
      "user_id": "karuna_jain_02082005",
      "email": "Karunajain230183@acropolis.in",
      "roll_number": "0827CS231125",
      "even_numbers": ["334"],
      "odd_numbers": ["1"],
      "alphabets": ["A", "Y", "B"],
      "special_characters": ["$"],
      "sum": "335",
      "concat_string": "ByA"
    }
    ```

---

## 🛠️ Local Setup & Run Instructions

### Prerequisites
*   **Java JDK 17+**
*   **Apache Maven 3.6+**

### 1. Build and Run via Maven
Clone/navigate to the `bfhl` directory and run:

```bash
# Build the project
mvn clean install

# Run tests
mvn test

# Start the Spring Boot application
mvn spring-boot:run
```

The application will start on: **`http://localhost:8080`**

### 2. Run via Docker
```bash
# Build the Docker image
docker build -t bfhl-api .

# Run the Docker container
docker run -p 8080:8080 bfhl-api
```

---

## 🧪 Testing the API
You can test the running API using `curl`:

```bash
# Test GET endpoint
curl -X GET http://localhost:8080/bfhl

# Test POST endpoint
curl -X POST http://localhost:8080/bfhl \
  -H "Content-Type: application/json" \
  -d '{"data": ["a", "1", "334", "$", "y", "B"]}'
```

---

## 🌐 Production Deployment

### 1. Deploy on Render (Docker Web Service) - Recommended & Free
1. Push this project folder to your GitHub repository.
2. Log in to [Render](https://render.com).
3. Click **New +** -> **Web Service**.
4. Connect your GitHub repository.
5. In the settings:
   *   **Name:** `bfhl-api`
   *   **Root Directory:** `src/bhfl/bfhl` (or leave empty if it's the root of your repo)
   *   **Runtime:** `Docker`
6. Click **Deploy Web Service**. Render will build the container from the `Dockerfile` and expose it.

### 2. Deploy on Railway
1. Sign in to [Railway.app](https://railway.app/).
2. Click **New Project** -> **Deploy from GitHub repo**.
3. Connect your repository and select the branch.
4. Railway will automatically detect the `Dockerfile` or Maven configuration and build/deploy your Spring Boot service.
