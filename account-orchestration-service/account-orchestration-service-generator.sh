mvn clean install
docker build --tag ams/account-orchestration-service:001 .
docker-compose up -d