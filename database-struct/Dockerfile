FROM openjdk:17
ENV APP_HOME=usr/app
WORKDIR ${APP_HOME}
COPY /target/*.jar db-struct.jar
CMD ["java","-jar","db-struct.jar"]