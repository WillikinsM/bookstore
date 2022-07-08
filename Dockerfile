FROM openjdk:11-jre-slim

WORKDIR /bookstore
COPY ./target/bookstore-api-0.0.1-SNAPSHOT.jar /bookstore

EXPOSE 8080

CMD ["java","-jar","bookstore-api-0.0.1-SNAPSHOT.jar"]

