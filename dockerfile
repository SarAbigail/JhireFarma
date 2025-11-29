# Etapa de construcción
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copiar pom y código desde la subcarpeta "botica"
COPY botica/pom.xml .
COPY botica/src ./src

# Build del JAR
RUN mvn -DskipTests package

# Etapa de runtime
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copiar el JAR generado desde la imagen anterior
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
