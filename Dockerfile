FROM openjdk:17-jdk
ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} order-project.jar
ENTRYPOINT ["java","-jar","/order-project.jar","--spring.profiles.active=prod"]

RUN ln -snf /usr/share/zoneinfo/Asia/Seoul /etc/localtime
