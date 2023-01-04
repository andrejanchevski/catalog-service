# catalog-service

# Commands for Running the Service

1. "./gradlew test" - Running the tests for Catalog Service
2. "./gradlew bootRun" - Runnint the Catalog Service (on default port 8080)

# Running in Docker Container
1. "./gradlew bootBuildImage"
2. "docker run --rm --name catalog-service -p 8080:8080 catalog-service:0.0.1-SNAPSHOT"

# Running in Kubernetes
1. For local running have minikube running locally
2. minikube image load catalog-service:0.0.1-SNAPSHOT
   2.1. Loading the image into minikube from the local machine
   2.2. It is important to execute ./gradlew bootBuildImage first to get the image locally using Cloud Native Buildpacks
3. kubectl create deployment catalog-service --image=catalog-service:0.0.1-SNAPSHOT
4. kubectl expose deployment catalog-service --name=catalog-service --port=8080
5. kubectl port-forward catalog-service 8080:8080
   5.1. The first port is the local port