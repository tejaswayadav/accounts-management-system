cd account-orchestration-service/
mvn clean install
docker build --tag ams/account-orchestration-service:001 .
cd ../

cd accounts-service/
mvn clean install
docker build --tag ams/accounts-service:001 .
cd ../

cd collection-service/
mvn clean install
docker build --tag ams/collection-service:001 .
cd ../

cd keys-service/
mvn clean install
docker build --tag ams/keys-service:001 .
cd ../

cd engine-service/
mvn clean install
docker build --tag ams/engine-service:001 .
cd ../

docker-compose up -d