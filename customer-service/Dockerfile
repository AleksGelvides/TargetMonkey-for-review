FROM openjdk:17
ENV APP_HOME=usr/app
WORKDIR ${APP_HOME}
COPY /customer-service-impl/target/*.jar customer-service.jar
EXPOSE 8081
CMD ["java","-jar","customer-service.jar"]