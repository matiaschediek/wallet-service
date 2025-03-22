# 🚀 Wallet Service

## 📌 Features 🎉🎯💰
This service manages digital wallets for users, supporting the following operations:

- **Create Wallet**: Allows users to create wallets.
- **Check Current Balance**: Retrieves the available balance in a wallet.
- **Check Historical Balance**: Retrieves the balance of a wallet at a specific point in the past.
- **Deposit Funds**: Adds money to a wallet.
- **Withdraw Funds**: Allows users to withdraw money from a wallet.
- **Transfer Funds**: Facilitates money transfers between users.

---

### **Technology Stack** 📦💻🚀
- **Spring Boot** (Microservice framework)
- **MongoDB** (NoSQL database)
- **Docker & Docker Compose** (Infrastructure management)
- **Gradle** (Dependency management)
- **Testcontainers & JUnit** (Integration testing)
- **Logback** (Logging framework)

---

## 🎯 Technical Decisions 🧠📊⚡
1. **MongoDB for Persistence**: Chosen for its flexible JSON-based document storage and powerful querying capabilities.
2. **Hexagonal Architecture**: Ensures clean separation between business logic and infrastructure components.
3. **Testcontainers for Testing**: Provides an isolated test environment for MongoDB.
4. **Swagger UI for API Documentation**: Allows developers to interact with the API and understand its capabilities.
5. **Gradle for Dependency Management**: Simplifies the build process and manages project dependencies.
6. **Cucumber for BDD**: Supports behavior-driven development with Gherkin syntax.
7. **Separate Logging Mechanism**:
   - **Application Logs**: For debugging and monitoring.
   - **Audit Logs**: To track financial transactions and ensure traceability.

---

## 🚀 Quick Start with Docker 🐳📦⚡

### 1️⃣ Clone the Repository 🏁
```bash
git clone git@github.com:matiaschediek/wallet-service.git
cd wallet-service
```

### 2️⃣ Start the Service with Docker Compose 🏗️
```bash
docker-compose up -d
```
This will start:
- **MongoDB** on port `27017`.
- **Wallet Service** running as a container.

### 3️⃣ Verify Running Containers 🛠️
```bash
docker ps
```

### 4️⃣ Access the Wallet Service 🌐
- **Swagger UI**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Testing the Service 🧪🔬🚀

```bash
./gradlew clean test
```

## Next Steps 🚀🔜📈
- **Implement Security**: Add authentication and authorization mechanisms.
  - **JWT**: Secure API endpoints with JSON Web Tokens.
  - **Kong Gateway**: Integrate with Kong for API management and security.
- **Monitoring & Alerting**: Implement health checks and monitoring with Prometheus and Grafana.
- **CI/CD Pipeline**: Automate the build and deployment process with Jenkins or GitLab CI.
- **Domain Events**: Implement domain events for better decoupling and scalability.
  - SNS/SQS: Use AWS SNS/SQS for asynchronous communication between microservices.
- **Cache Layer**: Introduce a caching layer to improve performance and reduce database load.

---
