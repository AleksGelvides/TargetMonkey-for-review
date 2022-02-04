FROM openjdk:17
COPY ./data-access-rest/target/*.jar data-access-service.jar
EXPOSE 8081
CMD ["java","-jar","data-access-service.jar"]