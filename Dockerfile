FROM openjdk:8u171-jdk-alpine3.8
LABEL maintainer="tiagompalte"

ENV LANG C.UTF-8

RUN apk add --update bash

ADD build/libs/*.jar /app/app.jar

CMD java -jar /app/app.jar $APP_OPTIONS

#docker run -p 8080:8080 -p --network beer-net -e APP_OPTIONS='--spring.datasource.url=jdbc:postgresql://beerdb:5432/beerstore' tiagompalte/beerstore:0.1