FROM openjdk:8-jre-alpine
WORKDIR /account-management-system
COPY ./target/*.jar /engine-service/app.jar
CMD ["/usr/bin/java","-jar","/engine-service/app.jar"]
EXPOSE 5004