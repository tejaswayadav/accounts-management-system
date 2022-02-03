FROM openjdk:8-jre-alpine
WORKDIR /account-management-system
COPY ./target/*.jar /account-orchestration-service/app.jar
CMD ["/usr/bin/java","-jar","/account-orchestration-service/app.jar"]
EXPOSE 5000