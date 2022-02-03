FROM openjdk:8-jre-alpine
WORKDIR /account-management-system
COPY ./target/*.jar /collection-service/app.jar
CMD ["/usr/bin/java","-jar","/collection-service/app.jar"]
EXPOSE 5002