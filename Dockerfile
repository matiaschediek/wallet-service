FROM gradle:latest AS build

WORKDIR /app

COPY . .
RUN gradle build --no-daemon

FROM openjdk:21-jdk-slim AS run

WORKDIR /app

COPY --from=build /app/build/libs/wallet-service-1.0.0.jar wallet-service.jar

EXPOSE 8080

CMD ["java", "-jar", "wallet-service.jar"]