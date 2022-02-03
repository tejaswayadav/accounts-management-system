mvn clean install
docker build --tag ams/keys-service:001 .
docker-compose up -d