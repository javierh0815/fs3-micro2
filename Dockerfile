FROM eclipse-temurin:21-jdk AS build

WORKDIR /app
COPY .mvn .mvn
COPY mvnw pom.xml ./
RUN sed -i 's/\r$//' mvnw && chmod +x mvnw && ./mvnw -q -B dependency:go-offline
COPY src src
RUN ./mvnw -q -B clean package -DskipTests


FROM eclipse-temurin:21-jre

WORKDIR /app
RUN apt-get update \
    && apt-get install -y --no-install-recommends unzip \
    && rm -rf /var/lib/apt/lists/* \
    && useradd --system --no-create-home appuser \
    && mkdir -p /app/wallet \
    && chown appuser:appuser /app/wallet \
    && chmod 0700 /app/wallet
COPY --from=build /app/target/*.jar /app/threads.jar
COPY docker-entrypoint.sh /app/docker-entrypoint.sh

ENV TNS_ADMIN=/app/wallet
RUN chmod 0555 /app/docker-entrypoint.sh
USER appuser
EXPOSE 8081

ENTRYPOINT ["/app/docker-entrypoint.sh"]
