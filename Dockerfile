FROM gradle:6.8.2-jdk8 AS build
LABEL name = coordinador-de-participantes-gradle
COPY --chown=gradle:gradle . /home/gradle/src
RUN apt-get update && apt-get install -y locales && rm -rf /var/lib/apt/lists/* \
	&& localedef -i en_US -c -f UTF-8 -A /usr/share/locale/locale.alias en_US.UTF-8
ENV LANG en_US.UTF-8
WORKDIR /home/gradle/src
RUN gradle shadowJar --no-daemon

FROM openjdk:8-jre-slim
WORKDIR /home/gradle/src
LABEL name = coordinador-de-participantes-app

EXPOSE 80:8080

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/coordinador-de-participantes-application.jar

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/coordinador-de-participantes-application.jar"]