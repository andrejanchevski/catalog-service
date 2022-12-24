# catalog-service

# Commands for Running the Service

1. "./gradlew test" - Running the tests for Catalog Service
2. "./gradlew bootRun" - Runnint the Catalog Service (on default port 8080)
3. "./gradlew bootBuildImage"
  3.1 "docker run --rm --name catalog-service -p 8080:8080 catalog-service:0.0.1-SNAPSHOT"
  3.2 Running Catalog Service in Docker
