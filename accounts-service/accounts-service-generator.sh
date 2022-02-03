mvn clean install
docker build --tag ams/accounts-service:001 .
docker-compose up -d