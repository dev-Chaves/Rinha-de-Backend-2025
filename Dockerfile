FROM eclipse-temurin:21-alpine AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 9999

ENTRYPOINT [ "java", "-jar", "app.jar" ]