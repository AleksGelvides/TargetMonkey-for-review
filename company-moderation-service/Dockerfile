FROM openjdk:17
ENV APP_HOME=usr/app
WORKDIR ${APP_HOME}
COPY /target/*.jar company-moderation-service.jar
EXPOSE 8081
CMD ["java","-jar","company-moderation-service.jar"]