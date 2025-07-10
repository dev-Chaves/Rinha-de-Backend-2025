# Environment Configuration Setup

This document explains the environment configuration setup for the Rinha Backend project.

## Changes Made

### 1. Java Version Fix
- **Problem**: The project was configured to use Java 24, which is not available in the system
- **Solution**: Changed Java version from 24 to 21 in both `pom.xml` and `Dockerfile`
- **Files modified**: 
  - `pom.xml`: Updated `<java.version>21</java.version>`
  - `Dockerfile`: Updated base images to use `eclipse-temurin:21-alpine` and `eclipse-temurin:21-jdk-alpine`

### 2. Environment Variables Configuration
- **Created**: `.env` file with all required environment variables for Docker Compose
- **Variables defined**:
  ```
  POSTGRES_USER=rinhauser
  POSTGRES_PASSWORD=rinhapass
  POSTGRES_DB=rinhadb
  SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/rinhadb
  SPRING_DATASOURCE_USERNAME=rinhauser
  SPRING_DATASOURCE_PASSWORD=rinhapass
  ```

### 3. Application Configuration Enhancement
- **Enhanced**: `src/main/resources/application.yml` with complete database configuration
- **Added**:
  - PostgreSQL datasource configuration with environment variable support
  - JPA/Hibernate configuration
  - Logging configuration
  - Default values for local development

### 4. Docker Compose Fix
- **Fixed**: Volume name mismatch in `compose.yml` (changed from `pgdata` to `db_data`)

## Architecture Overview

The application now has a complete environment setup with:

1. **API Service** (`api`):
   - Built from local Dockerfile
   - Runs on port 9999
   - Connects to PostgreSQL database
   - Uses environment variables for database connection

2. **Database Service** (`db`):
   - PostgreSQL 16
   - Runs on port 5432
   - Configured with health checks
   - Persistent data storage with volume

3. **Payment Processor Services**:
   - Two instances for redundancy
   - External image: `zanfranceschi/payment-processor`
   - Ports 8001 and 8002

## Networks
- `backend`: For API and database communication
- `payment-processor`: For payment processor services

## How to Run

1. **Using Docker Compose**:
   ```bash
   docker-compose up --build
   ```

2. **Local Development**:
   ```bash
   ./mvnw spring-boot:run
   ```
   (Requires local PostgreSQL instance)

## Environment Variables

All environment variables are centralized in the `.env` file and automatically loaded by Docker Compose. The Spring Boot application uses these variables with fallback defaults for local development.

## Build Status
✅ Maven build successful
✅ Docker Compose configuration valid
✅ Environment variables properly configured
✅ Java version compatibility resolved