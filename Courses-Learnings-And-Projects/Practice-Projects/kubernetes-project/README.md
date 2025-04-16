
# Kubernetes Spring Boot Project with Docker and Kubernetes

This project demonstrates how to create a Spring Boot application, containerize it with Docker, and deploy it to a Kubernetes cluster with multiple nodes. It includes:

- A Spring Boot application that connects to a PostgreSQL database.
- Docker configuration to build and run containers for both the application and the database.
- Kubernetes configuration to deploy the application and database with multiple nodes.

## Project Structure

The project includes the following components:

1. **Spring Boot Application**: 
   - A simple REST API that interacts with a PostgreSQL database.
   - Contains entity, repository, service, and controller layers.

2. **Docker Configuration**:
   - **Dockerfile**: Used to build a Docker image for the Spring Boot application.
   - **docker-compose.yml**: A Docker Compose configuration to spin up the application and the database in containers.

3. **Kubernetes Configuration**:
   - Deployment and service YAML files to deploy the application and the database in a Kubernetes cluster.
   - The project uses 3 nodes in the Kubernetes cluster for scaling.

## Prerequisites

Before getting started, ensure you have the following tools installed:

- **Docker**: [Install Docker](https://docs.docker.com/get-docker/)
- **Docker Compose**: [Install Docker Compose](https://docs.docker.com/compose/install/)
- **Kubernetes**: [Install kubectl](https://kubernetes.io/docs/tasks/tools/install-kubectl/)
- **Minikube or Kubernetes Cluster**: Ensure you have a running Kubernetes cluster (local or cloud).

## Getting Started

### 1. Clone the Repository

Clone this repository to your local machine:

```bash
git clone https://github.com/your-username/your-repository.git
cd your-repository
```

### 2. Build the Docker Image

To build the Docker image for the Spring Boot application, run the following command:

```bash
docker build -t your-dockerhub-username/your-image-name:latest .
```

### 3. Run the Application Using Docker Compose

The `docker-compose.yml` file is configured to run both the PostgreSQL database and the Spring Boot application.

Run the following command to start the services:

```bash
docker-compose up --build
```

This will start the containers and expose the Spring Boot application on port `8080` and the PostgreSQL database on port `5433`.

### 4. Deploy to Kubernetes

To deploy the project to a Kubernetes cluster:

1. Apply the Kubernetes deployment files for the PostgreSQL database:

    ```bash
    kubectl apply -f k8s/postgres-deployment.yaml
    ```

2. Apply the deployment files for the Spring Boot application:

    ```bash
    kubectl apply -f k8s/product-app-deployment.yaml
    ```

3. Expose the application as a service (if not already done in the deployment YAML):

    ```bash
    kubectl expose deployment product-app --type=NodePort --name=product-app-service
    ```

4. Verify that the pods are running:

    ```bash
    kubectl get pods -n kubernetes-dashboard
    ```

    You should see the `postgres` and `product-app` pods running.

![image](https://github.com/user-attachments/assets/3d119e4c-70a0-45b1-8eba-5a99417e8705)
![image](https://github.com/user-attachments/assets/76eebbb0-0710-48e1-aff4-5d5275376367)

### 5. Access the Application

You can access the Spring Boot application by forwarding the Kubernetes service:

```bash
kubectl port-forward svc/product-app-service 8080:8080
```

Now you can access the app locally at `http://localhost:8080`.

### 6. Access the PostgreSQL Database

You can connect to the PostgreSQL database running inside Kubernetes via port-forwarding:

```bash
kubectl port-forward svc/postgres 5433:5432
```

You can then connect to the database on `localhost:5433` using any PostgreSQL client.

## Configuration

- **Database Configuration**: The Spring Boot application uses PostgreSQL, with the following configuration:

  - **Host**: `postgres`
  - **Port**: `5433`
  - **Username**: `postgres`
  - **Password**: `sameer`
  - **Database Name**: `kubernetes_project_db`

- **Spring Boot Configuration**: The application reads the database configuration from the `application.properties` file, which is set via environment variables in the Docker and Kubernetes configurations.

## Useful Commands

### Docker Commands

- Build the Docker image:

  ```bash
  docker build -t your-dockerhub-username/your-image-name:latest .
  ```

- Run Docker Compose:

  ```bash
  docker-compose up --build
  ```

### Kubernetes Commands

- Deploy the PostgreSQL and Spring Boot applications:

  ```bash
  kubectl apply -f k8s/postgres-deployment.yaml
  kubectl apply -f k8s/product-app-deployment.yaml
  ```

- Expose the Spring Boot application service:

  ```bash
  kubectl expose deployment product-app --type=NodePort --name=product-app-service
  ```

- Check pod status:

  ```bash
  kubectl get pods -n kubernetes-dashboard
  ```

- Port-forward to access the app:

  ```bash
  kubectl port-forward svc/product-app-service 8080:8080
  ```

- Port-forward to access PostgreSQL:

  ```bash
  kubectl port-forward svc/postgres 5433:5432
  ```

## Troubleshooting

- If you encounter issues with the proxy, check if all services and deployments are running properly.
- Make sure that the ports for both the Spring Boot application and the database are exposed correctly.
- Use `kubectl logs` to check the logs for any errors related to the application or the database.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
