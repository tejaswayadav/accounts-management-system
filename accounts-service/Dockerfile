FROM openjdk:8-jre-alpine
WORKDIR /account-management-system
COPY ./target/*.jar /accounts-service/app.jar
CMD ["/usr/bin/java","-jar","/accounts-service/app.jar"]
EXPOSE 5001