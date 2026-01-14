FROM eclipse-temurin:21-jdk AS builder
WORKDIR /build
COPY . .
RUN ./mvnw clean package -DskipTests 
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /build/target/POS-0.0.1-SNAPSHOT.jar app.jar
COPY ./src/main/resources/application-prod.properties /app/config/application-prod.properties
COPY ./.env /app
ENV SPRING_CONFIG_LOCATION=/app/config/application-prod.properties
ENV SPRING_PROFILES_ACTIVE=prod
ENTRYPOINT ["java", "-jar", "app.jar"]