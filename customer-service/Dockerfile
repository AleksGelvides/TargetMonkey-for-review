FROM openjdk:17
ENV APP_HOME=usr/app
WORKDIR ${APP_HOME}
COPY /customerJpa-service-impl/target/*.jar customerJpa-service.jar
EXPOSE 8081
CMD ["java","-jar","customerJpa-service.jar"]