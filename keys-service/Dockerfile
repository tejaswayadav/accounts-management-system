FROM openjdk:8-jre-alpine
WORKDIR /account-management-system
COPY ./target/*.jar /keys-service/app.jar
CMD ["/usr/bin/java","-jar","/keys-service/app.jar"]
EXPOSE 5003