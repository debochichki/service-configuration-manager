# Spring Config Service Demo Project

## Config Server with manual refresh

### Prerequisites:
- Java 21 or higher
- Docker

Running the service:
- source local/docker/db_credentials.env
- docker compose -f local/docker/docker-compose.yaml up -d
- ./mvnw clean install
- ./mvnw spring-boot:run