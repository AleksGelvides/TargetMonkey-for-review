FROM openjdk:17
ENV APP_HOME=usr/app
WORKDIR ${APP_HOME}
COPY /registration-service-impl/target/*.jar registration_service.jar
EXPOSE 8081
CMD ["java","-jar","registration_service.jar"]