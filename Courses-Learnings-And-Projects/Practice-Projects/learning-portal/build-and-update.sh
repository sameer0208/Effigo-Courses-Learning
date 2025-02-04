rm -rf /target
mvn clean install -DskipTests=true

docker build -t learning-portal:v1 .

docker-compose up -d
