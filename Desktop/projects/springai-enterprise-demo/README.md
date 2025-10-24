# Spring AI + OpenAI Enterprise Demo

Production-leaning example using **Spring Boot 3**, **Spring AI**, **OpenAI ChatClient**, and **pgvector** for RAG.

## Features
- Spring AI OpenAI integration via `ChatClient`
- RAG with pgvector (HNSW, cosine)
- Profiles (`default`, `prod`) and externalized secrets
- OpenAPI UI at `/swagger-ui.html`
- Dockerfile + docker-compose (Postgres + app)
- Helm chart and raw K8s manifests
- Actuator endpoints for health/metrics

## Quick Start (Local)
```bash
export OPENAI_API_KEY=sk-...
docker compose up --build -d
# wait a few seconds for Postgres to be healthy
curl -X POST http://localhost:8080/api/v1/ai/chat   -H 'Content-Type: application/json'   -d '{"question":"What is the refund policy?"}'
```

Open Swagger UI: http://localhost:8080/swagger-ui.html

## API
`POST /api/v1/ai/chat`
```json
{ "question": "your query" }
```

## Enterprise Notes
- Replace `DemoDataLoader` with ingestion pipelines (S3, SharePoint, Confluence).
- Add auth (OAuth2/JWT) via Spring Security and gateways (e.g., Spring Cloud Gateway).
- Enable tracing (OTel), logging (JSON), and rate limiting.
- For air‑gapped or regulated envs, swap `spring-ai-openai` with Azure OpenAI, Bedrock, or on‑prem models.

## Configuration
See `src/main/resources/application.yml`. For prod, use:
```bash
SPRING_PROFILES_ACTIVE=prod
OPENAI_API_KEY=...
DB_USER=ai_user
DB_PASSWORD=ai_pass
```

## Build & Run (No Docker)
```bash
export OPENAI_API_KEY=sk-...
./mvnw spring-boot:run
```

## Testing
```bash
mvn test
```
