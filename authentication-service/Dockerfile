FROM openjdk:17
ENV APP_HOME=usr/app
WORKDIR ${APP_HOME}
COPY /authentication-service-impl/target/*.jar authenticatation_service.jar
EXPOSE 8082
CMD ["java","-jar","authenticatation_service.jar"]