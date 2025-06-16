FROM openjdk:21-jdk-slim AS build

WORKDIR /src

COPY gradle.properties gradlew *.gradle.kts /src/
COPY gradle /src/gradle
COPY .git /src/.git
COPY api /src/api
COPY server /src/server
COPY data /src/data

RUN apt-get -y update && apt-get -y install git
RUN ./gradlew :server:shadowJar

FROM openjdk:21-jdk-slim AS run

LABEL maintainer="AllayMC"

COPY --from=build /src/server/build/libs/allay-server-*-shaded.jar /home/allaymc/jar/allay.jar

RUN useradd --user-group \
            --no-create-home \
            --home-dir /home/allaymc \
            --shell /usr/sbin/nologin \
            allaymc

EXPOSE 19132/tcp
EXPOSE 19132/udp

RUN mkdir -p /home/allaymc/data && \
    chown -R allaymc:allaymc /home/allaymc

USER allaymc:allaymc

VOLUME /home/allaymc/data

WORKDIR /home/allaymc/data

ENTRYPOINT ["java"]
CMD [ "-jar", "/home/allaymc/jar/allay.jar"]