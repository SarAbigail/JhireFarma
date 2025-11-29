FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY target/*.jar app.jar

ENV PORT=8080

EXPOSE 8080

CMD ["sh", "-c", "java -jar app.jar --server.port=$PORT"]
