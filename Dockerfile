FROM openjdk:8-jre-alpine
RUN apk --no-cache add curl
MAINTAINER dongzeguang

Env APP_NAME testbkci
Env APP_VERSION 1.0.0-SNAPSHOT

RUN mkdir -p /opt/app/${APP_NAME}
RUN mkdir -p /opt/logs/${APP_NAME}

ADD out/testbkci*.jar /opt/app/testbkci/app.jar

ENTRYPOINT ["java","-jar","/opt/app/testbkci/app.jar"]
