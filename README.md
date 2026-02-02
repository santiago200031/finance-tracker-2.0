# Finance Tracker 2.0

A backend application for tracking and analyzing financial investments with AI-powered insights. This project demonstrates the integration of modern Java technologies, AI/LLM capabilities, and robust data persistence strategies.

## Overview

Finance Tracker 2.0 is a REST API backend that tracks and analyzes investment data for:

- **Deka Global Champions** fund (investment fund tracking)
- **Bitcoin** (cryptocurrency tracking)

The application features an AI-powered chatbot for financial queries, automated data synchronization, chart generation, and historical price analysis.

> **Note on Architecture Evolution**: The application initially used CSV files for data storage but has been migrated to PostgreSQL for better scalability, data integrity, and concurrent access capabilities.

## Technology Stack & Architecture

### Core Framework

| Technology | Purpose | Why It's Used |
|------------|---------|---------------|
| **Quarkus 3.15.1** | Application Framework | Supersonic startup, minimal memory footprint, optimized for containers and cloud-native deployments |
| **Java 17** | Language | Modern Java features, long-term support, improved performance |

### API & REST Layer

| Technology | Purpose | Why It's Used |
|------------|---------|---------------|
| **Jakarta JAX-RS** | REST API | Industry standard for RESTful web services in Java |
| **Jackson** | JSON Serialization | Standard JSON processing library, excellent Spring/Quarkus integration |
| **SmallRye OpenAPI** | API Documentation | Automatic OpenAPI/Swagger UI generation for API discovery |

### Data Layer

| Technology | Purpose | Why It's Used |
|------------|---------|---------------|
| **PostgreSQL** | Primary Database | Robust, production-ready relational database with excellent Java support |
| **Hibernate ORM with Panache** | Data Access | Simplifies JPA operations with active record pattern, reduces boilerplate |
| **Flyway** | Database Migrations | Version control for database schema, ensures consistent deployments |
| **Quartz** | Job Scheduling | Enterprise-grade scheduling for periodic data synchronization tasks |

### AI & LLM Integration

| Technology | Purpose | Why It's Used |
|------------|---------|---------------|
| **LangChain4j 0.35.0** | LLM Framework | Java-native framework for building LLM applications, integrates with multiple providers |
| **Ollama** | Local LLM Inference | Runs AI models locally (gpt-oss:20b, qwen2.5-coder), keeps data private, no API costs |
| **Easy RAG** | Retrieval-Augmented Generation | Enables chatbot to answer questions based on document corpus in `easy-rag-catalog/` |

### External Integrations

| Technology | Purpose | Why It's Used |
|------------|---------|---------------|
| **Microsoft Finance REST API** | Market Data | Real-time financial data for investments |
| **Microsoft Chart API** | Chart Generation | Visual representation of financial data |
| **REST Client** | HTTP Communication | Type-safe REST client with JSON mapping |

### Development & Build

| Technology | Purpose |
|------------|---------|
| **Maven** | Build Tool |
| **Lombok** | Boilerplate Reduction |
| **Docker** | Containerization |

### Machine Learning

| Technology | Purpose | Why It's Used |
|------------|---------|---------------|
| **H2O.ai** | ML Model Inference | Production-grade machine learning model deployment and execution |

## Architecture

The application follows a **layered architecture** with clear separation of concerns:

│  Presentation Layer (REST API)
│  -- /finance-chatbot/*- AI-powered chat endpoints
│  -- /finance/* - Finance data endpoints
│  -- OpenAPI/Swagger UI documentation

│  Controller Layer
│  -- FinanceController - Orchestrates finance operations
│  -- PriceDifferenceController - Price analysis
│  -- FinanceBot - LangChain4j AI Service interface
├─────────────────────────────────────────────────────────
│  Service Layer
│  -- DekaFinanceService - Deka Global Champions ops
│  -- BTCFinanceService - Bitcoin operations
│  -- FinanceChartService - Chart generation
│  -- PriceDifferenceService - Historical analysis
├─────────────────────────────────────────────────────────
│  Data Access Layer
│  -- DekaFinanceRepository (JPA/Hibernate Panache)
│  -- BTCFinanceRepository
│  -- MicrosoftFinanceRestClient - External market data  
│  -- CustomLLMModelRestClient - LLM integration
├─────────────────────────────────────────────────────────
│  Data Storage
│  -- PostgreSQL (primary database)
│  -- CSV (!disabled, since migration to PostgreSQL)
│  -- RAG documents (easy-rag-catalog/)

## Key Features

### 1. Investment Tracking

- Real-time data synchronization from Microsoft Finance API
- Historical price data storage with PostgreSQL
- Scheduled jobs for periodic data updates (Quartz)

### 2. AI-Powered Chatbot

- Natural language queries about investments
- RAG (Retrieval-Augmented Generation) using documents in `easy-rag-catalog/`
- Local LLM inference via Ollama (privacy-preserving, cost-effective)
- Context-aware responses based on investment data

### 3. Price Analysis

- Historical price difference calculations
- Chart generation via Microsoft Chart API

### 4. Database Management

- Flyway migrations with versioning
- PostgreSQL triggers for data integrity
- Panache ORM for simplified data access

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.8+
- Docker (optional, for containerized deployment)
- PostgreSQL (optional, Quarkus DevServices provides one for development)
- Ollama (for AI chatbot functionality)

### Running in Development Mode

```bash
./mvnw compile quarkus:dev
```

The application will start with:

- Dev UI available at: <http://localhost:8080/q/dev/>
- Swagger UI at: <http://localhost:8080/q/swagger-ui/>
- API endpoints at: <http://localhost:8080/>

### Building for Production

```bash
./mvnw package
```

This produces the application in `target/quarkus-app/`.

### Docker Build

```bash
# JVM mode
./mvnw package -Dquarkus.container-image.build=true

# Native executable (requires GraalVM)
./mvnw package -Dnative
```

### AI/Chatbot Setup

1. Install Ollama locally
2. Pull the required models (and set the model to use in application.properties):

   ```bash
   ollama pull gpt-oss:20b
   ```

3. Place RAG documents in `easy-rag-catalog/` (PDF, ODT, TXT supported)

## API Endpoints

### Chatbot

- `POST /finance-chatbot` - Chat with the AI about your investments

### Finance Data

- `GET /finance/deka` - Get Deka Global Champions data
- `GET /finance/btc` - Get Bitcoin data
- `GET /finance/chart` - Generate investment charts
- `GET /finance/price-difference` - Get historical price analysis

## Configuration

Key configuration properties in `application.properties`:

```properties
# Database
quarkus.datasource.db-kind=postgresql

# AI Timeout
quarkus.langchain4j.ollama.chat-model.timeout=180s

# RAG Document Path
quarkus.langchain4j.easy-rag.path=./easy-rag-catalog/
```

## CI/CD

The project includes a GitHub Actions workflow (`.github/workflows/ci-mvn.yml`) that:

- Runs on JDK 17 (Temurin)
- Triggers on push/PR to `master`
- Builds and tests the application
- Note: Application was implemented at the moment to run just locally

## Skills Demonstrated

This project showcases proficiency in:

- **Java Enterprise Development** (Quarkus, Jakarta EE, Maven)
- **REST API Design** (JAX-RS, OpenAPI, JSON)
- **Database Design** (PostgreSQL, JPA/Hibernate, Flyway migrations)
- **AI/LLM Integration** (LangChain4j, RAG patterns, Ollama)
- **Microservices Architecture** (Layered design, dependency injection)
- **DevOps** (Docker containerization, CI/CD, native compilation)
- **External API Integration** (REST clients, third-party APIs)
