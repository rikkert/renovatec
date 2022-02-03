FROM openjdk:11.0-jdk-slim-buster as build
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:11.0-jre-slim-buster
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency
ARG PORT=8080
ENV SERVER_PORT=$PORT
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-noverify","-cp","app:app/lib/*","net.ripencc.compo.CompoApplication"]