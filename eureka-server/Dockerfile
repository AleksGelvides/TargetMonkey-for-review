FROM openjdk:17
ENV APP_HOME=usr/app
WORKDIR ${APP_HOME}
COPY /target/*.jar eureka.jar
EXPOSE 8761
CMD ["java","-jar","eureka.jar"]