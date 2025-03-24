# Spring Config Server Demo Project

## Config Server with automatic refresh using Spring Cloud Bus

### Prerequisites:
- Java 21 or higher
- Docker
- Kubernetes
- Kafka
- Helm

### Running the service:
- source local/docker/db_credentials.env
- docker compose -f local/docker/docker-compose.yaml up -d
- ./mvnw clean install -Pdocker
- envsubst < k8s/chart/values.yaml | helm install reports ./k8s/chart --namespace demo -f -
- kubectl port-forward -n demo service/reports-svc 8010:8080