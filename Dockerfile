FROM eclipse-temurin:11-jdk-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw.cmd .
COPY src src
RUN chmod +x mvnw.cmd 2>/dev/null || true
RUN ./mvnw.cmd clean package -DskipTests 2>/dev/null || \
    apk add --no-cache maven && mvn clean package -DskipTests

FROM eclipse-temurin:11-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
