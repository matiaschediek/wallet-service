# 🚀 Wallet Service

## 📌 Features 🎉🎯💰
This service manages digital wallets for users, supporting the following operations:

- **Create Wallet**: Allows users to create wallets.
- **Check Current Balance**: Retrieves the available balance in a wallet.
- **Check Historical Balance**: Retrieves the balance of a wallet at a specific point in the past.
- **Deposit Funds**: Adds money to a wallet.
- **Withdraw Funds**: Allows users to withdraw money from a wallet.
- **Transfer Funds**: Facilitates money transfers between users.
- **Event Notifications**: Publishes events to AWS SNS when wallets are created or transactions occur.
- **Audit & Application Logging**: Stores audit logs separately from application logs.

---

## ⚙️ Architecture 🏗️📡🔧
The service follows the **Hexagonal Architecture** approach, divided into the following layers:

- **Application Layer**: Implements use cases and business logic.
- **Domain Layer**: Contains entities and business rules.
- **Infrastructure Layer**: Adapters for MongoDB, AWS SNS, and logging.
- **Web Interface**: REST endpoints exposed using Spring Boot.

### **Technology Stack** 📦💻🚀
- **Spring Boot** (Microservice framework)
- **MongoDB** (NoSQL database)
- **AWS SNS** (Event notification system)
- **Docker & Docker Compose** (Infrastructure management)
- **Gradle** (Dependency management)
- **Testcontainers & JUnit** (Integration testing)
- **Logback** (Logging framework)

---

## 🎯 Technical Decisions 🧠📊⚡
1. **MongoDB for Persistence**: Chosen for its flexible JSON-based document storage and powerful querying capabilities.
2. **AWS SNS for Events**: Enables a decoupled event-driven architecture for seamless system integration.
3. **Hexagonal Architecture**: Ensures clean separation between business logic and infrastructure components.
4. **Testcontainers for Testing**: Provides an isolated test environment for MongoDB and SNS.
5. **Separate Logging Mechanism**:
   - **Application Logs**: For debugging and monitoring.
   - **Audit Logs**: To track financial transactions and ensure traceability.

---

## 🚀 Quick Start with Docker 🐳📦⚡

### 1️⃣ Clone the Repository 🏁
```bash
git clone https://github.com/your-repo/wallet-service.git
cd wallet-service
```

### 2️⃣ Start the Service with Docker Compose 🏗️
```bash
docker-compose up -d
```
This will start:
- **MongoDB** on port `27017`.
- **LocalStack (AWS SNS)** on port `4566`.
- **Wallet Service** running as a container.

### 3️⃣ Verify Running Containers 🛠️
```bash
docker ps
```

---

## 📄 Additional Documentation 📜📌📚
For API details and usage examples, check the repository documentation or use **Postman** to test endpoints.

---

🚀 **You're all set! Enjoy the Wallet Service!** 🎉🔥💳


