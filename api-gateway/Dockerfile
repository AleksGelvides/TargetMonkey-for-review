FROM openjdk:17
ENV APP_HOME=usr/app
WORKDIR ${APP_HOME}
COPY /target/*.jar api-gateway.jar
EXPOSE 8089:8089
CMD ["java","-jar","api-gateway.jar"]