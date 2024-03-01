FROM eclipse-temurin:17.0.10_7-jre

RUN mkdir /apps

COPY build/libs/application-0.0.1-SNAPSHOT.jar /apps/app.jar

WORKDIR /apps

ENTRYPOINT exec java ${JAVA_OPTS} -jar /apps/app.jar
